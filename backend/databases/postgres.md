# PostgreSQL – quick notes for Agoda-style booking

## TOPICS -
1. Multi index
2. tsrange (timestamp range)
3. gIST
4. GIN

## Quick Definitions

- **tsrange**: Range type for timestamps. `tsrange('2026-01-01', '2026-01-05')` stores an interval; use `&&` to check overlaps (e.g., booking conflicts).
  - *Under the hood*: Stores two timestamps (lower, upper) + boundary flags `[)` or `[]`. Operators like `&&` (overlap) compare bounds directly: `r1.lower < r2.upper AND r1.upper > r2.lower`. No special index needed—just efficient comparison logic.

- **GiST (Generalized Search Tree)**: Index for complex data (ranges, geometry). Enables exclusion constraints like "prevent overlapping bookings for same room".
  - *Under the hood*: Balanced tree where internal nodes store predicates/bounding boxes (e.g., for ranges: "this subtree contains ranges between [min, max]"). Query descends tree, pruning branches that can't match. Leaf nodes point to heap rows. Can be lossy—might return false positives that get rechecked.

- **GIN (Generalized Inverted Index)**: Index for multi-value data (arrays, full-text). Fast for `ILIKE '%search%'` and similarity matching on text fields.
  - *Under the hood*: Breaks values into keys (e.g., trigrams for text: "marriott" → "mar", "arr", "rri", "rio"...). Each key maps to posting list (sorted row IDs). Query intersects/merges posting lists. Example: search "mar" → fetch posting list for "mar", return matching rows.

---

## 1. Multiple indexes on a table

- One primary key (e.g., `booking_id`), many secondary indexes.
- Good when:
  - Different frequent queries need different WHERE patterns.
  - You care about read latency.

Example (booking):

```sql
CREATE INDEX idx_booking_room_dates
  ON booking (room_id, check_in, check_out);

CREATE INDEX idx_booking_user_date
  ON booking (user_id, check_in);
```

Use cases:

- `idx_booking_room_dates`: "is this room free for this date range?"
- `idx_booking_user_date`: "all bookings of a user in a time period".[web:70][web:76]

Trade-offs:

- Pros: much faster SELECTs for those patterns.
- Cons: extra disk, slower INSERT/UPDATE/DELETE because each index must be updated.[web:68][web:35][web:74]

---

## 2. `tsrange` for booking intervals

- `tsrange` stores `[check_in, check_out)` as one column.
- Gives range operators like `&&` (overlaps).[web:16]

Example:

```sql
ALTER TABLE booking
  ADD COLUMN booking_period tsrange
  GENERATED ALWAYS AS (
    tsrange(check_in, check_out, '[)')
  ) STORED;
```

Availability check:

```sql
SELECT 1
FROM booking
WHERE room_id = :room_id
  AND booking_status IN ('IN_PROGRESS','CONFIRMED')
  AND booking_period && tsrange(:ci, :co, '[)')
LIMIT 1;
```

---

## 3. GiST + exclusion constraint for "no double booking"

- GiST index supports range operators efficiently.[web:22]
- Exclusion constraint lets you say: "for same room_id, ranges must not overlap".

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

Result:

- DB rejects overlapping bookings at INSERT/UPDATE time.
- Application code doesn't have to manually handle all race conditions.[web:16][web:72]

---

## 4. GIN + `pg_trgm` for hotel-name search

- `pg_trgm` splits text into 3‑char chunks; GIN indexes them.
- Makes `ILIKE '%term%'` and similarity search fast.[web:53][web:73][web:75]

```sql
CREATE EXTENSION IF NOT EXISTS pg_trgm;

CREATE INDEX idx_hotel_name_trgm
  ON hotel USING gin (name gin_trgm_ops);

SELECT hotel_id, name
FROM hotel
WHERE name ILIKE '%' || :q || '%'
ORDER BY similarity(name, :q) DESC
LIMIT 7;
```

Use when:

- Need fuzzy / contains match on hotel names.
- Dataset is not huge enough to justify Elasticsearch yet.[web:45][web:54]
