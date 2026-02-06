# Agoda-style room booking backend – short HLD

Goal: show how Postgres + Elasticsearch work together to search hotels and book rooms safely.

---

## 1. Core components

- PostgreSQL
  - Tables: user, hotel, room, booking, payment.
  - Constraints: foreign keys, exclusion constraint for "no double booking".
  - Indexes: room/date ranges, user/date, hotel name trigram.
- Elasticsearch
  - Index: `hotels` for text search by name/city/tags.
- Redis
  - Payment idempotency keys.
- Kafka (or similar)
  - Events from Postgres → ES, and payment events.[web:49][web:55]

---

## 2. Minimal schemas (simplified)

Postgres:

```sql
CREATE TABLE hotel (
  hotel_id bigserial PRIMARY KEY,
  name     text NOT NULL,
  city     text NOT NULL
);

CREATE TABLE room (
  room_id     bigserial PRIMARY KEY,
  hotel_id    bigint NOT NULL REFERENCES hotel(hotel_id),
  room_number text NOT NULL,
  UNIQUE (hotel_id, room_number)
);

CREATE TABLE booking (
  booking_id     bigserial PRIMARY KEY,
  room_id        bigint NOT NULL REFERENCES room(room_id),
  user_id        bigint NOT NULL,
  check_in       timestamp NOT NULL,
  check_out      timestamp NOT NULL,
  booking_period tsrange GENERATED ALWAYS AS (
                   tsrange(check_in, check_out, '[)')
                 ) STORED,
  booking_status text NOT NULL,  -- IN_PROGRESS, CONFIRMED, CANCELLED
  payment_status text NOT NULL,  -- PENDING, PAID, FAILED
  amount         numeric(10,2) NOT NULL
);
```

No double booking:

```sql
CREATE EXTENSION IF NOT EXISTS btree_gist;

ALTER TABLE booking
ADD CONSTRAINT booking_no_overlap
EXCLUDE USING gist (
  room_id WITH =,
  booking_period WITH &&
)
WHERE (booking_status IN ('IN_PROGRESS','CONFIRMED'));
```

Key indexes:

```sql
CREATE INDEX idx_booking_room_period
  ON booking USING gist (room_id, booking_period);

CREATE INDEX idx_booking_user_date
  ON booking (user_id, check_in);
```

Hotel name search (Postgres option):

```sql
CREATE EXTENSION IF NOT EXISTS pg_trgm;
CREATE INDEX idx_hotel_name_trgm
  ON hotel USING gin (name gin_trgm_ops);
```

---

## 3. ES hotel index (search side)

Document example:

```json
{
  "hotel_id": 123,
  "name": "Marriott Chennai Beach",
  "city": "Chennai",
  "rating": 4.5
}
```

Simple mapping:

```json
PUT /hotels
{
  "mappings": {
    "properties": {
      "hotel_id": { "type": "keyword" },
      "name":     { "type": "text" },
      "city":     { "type": "text" },
      "rating":   { "type": "float" }
    }
  }
}
```

Search query (top 7):

```json
GET /hotels/_search
{
  "size": 7,
  "query": {
    "multi_match": {
      "query": "mar chennai",
      "fields": ["name^3", "city"],
      "fuzziness": "AUTO"
    }
  }
}
```

---

## 4. High-level flows

### A) Hotel search

1. User types "mar ch".
2. API → Search Service.
3. Search Service → Elasticsearch (`/hotels/_search`) → get top 7 hotel_ids.
4. Search Service optionally queries Postgres or cache for:
   - Latest price / availability for those hotels.
5. Response to client.

ES does ranking via inverted index; Postgres ensures correct availability.[web:49][web:55]

---

### B) Check availability and create booking

1. User selects hotel, room, date range.
2. Booking Service (DB transaction):
   - Query:

```sql
SELECT 1
FROM booking
WHERE room_id = :room_id
  AND booking_status IN ('IN_PROGRESS','CONFIRMED')
  AND booking_period && tsrange(:ci, :co, '[)')
LIMIT 1;
```

   - If row exists → room not available.
   - Else `INSERT` booking with `booking_status = 'IN_PROGRESS', payment_status = 'PENDING'`.

3. Exclusion constraint + GiST ensure no overlapping bookings slip through even under concurrency.[web:16][web:22]

---

### C) Payment (idempotent)

Short version:

1. Build `idempotency_key` (user + room + dates).
2. Store key in Redis and `payment_idempotency` table with status `IN_PROGRESS`.
3. Call Payment Service → gateway (RBI/Visa/etc.).
4. On success:
   - `payment.status = 'SUCCESS'`.
   - `booking.payment_status = 'PAID'`, `booking.booking_status = 'CONFIRMED'`.
   - Key set to `SUCCESS`.
5. On failure/timeouts:
   - Mark `FAILED`, possibly cancel booking, clear/mark key accordingly.

If Payment Service goes down but provider succeeds:

- Provider sends async callback or Kafka message.
- Worker reads event, checks idempotency key, and updates DB idempotently (no double charge).

---

## 5. Mental picture (for drawing later)

You can sketch:

- Left side: User → API Gateway.
- Middle:
  - Search Service → Elasticsearch (hotel index).
  - Booking Service → PostgreSQL (hotel/room/booking/payment).
  - Redis for idempotency.
  - Kafka for async events.
- Right side: Payment Provider (RBI/Visa/etc.).

For visuals/inspiration:

- ES cluster diagrams:
  - https://www.elastic.co/guide/en/elasticsearch/reference/current/scalability.html[web:49]
- Inverted index visuals:
  - https://www.geeksforgeeks.org/dbms/inverted-index/[web:59]
- Postgres range/index docs:
  - https://www.postgresql.org/docs/current/rangetypes.html[web:16]
