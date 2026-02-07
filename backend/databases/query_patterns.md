# N+1 query problem – brief notes

## 1. What is N+1?

Pattern:

- 1 query to fetch a list of items.
- Then N more queries (one per item) to fetch related data.
- Total = N + 1 queries → too many round-trips, slow at scale.

Example:

```sql
-- 1) Get 50 hotels
SELECT id, name FROM hotel LIMIT 50;

-- 2) For each hotel_id:
SELECT COUNT(*) FROM booking WHERE hotel_id = :hotel_id;
```

Result: 1 + 50 queries instead of 1–2 well-written queries.

---

## 2. How to fix / avoid it

### A) Use joins / IN to fetch in bulk

- Replace per-row queries with a single joined or batched query.

Example (hotel + booking count):

```sql
SELECT h.id,
       h.name,
       COUNT(b.id) AS booking_count
FROM hotel h
LEFT JOIN booking b ON b.hotel_id = h.id
GROUP BY h.id, h.name
LIMIT 50;
```

Or:

```sql
-- 1) Get 50 hotels
SELECT id, name FROM hotel LIMIT 50;

-- 2) Get all bookings for those hotels in one go
SELECT hotel_id, COUNT(*) AS booking_count
FROM booking
WHERE hotel_id IN (list_of_50_ids)
GROUP BY hotel_id;
```

Then combine results in application code.

---

## 3. Agoda-style example (fixed)

Use case: "show 100 hotels and their last 3 bookings".

Bad (N+1):

- 1 query for hotels.
- 100 queries for bookings per hotel.

Better:

- Query hotels once.
- Query bookings once with `hotel_id IN (...)` and `ORDER BY hotel_id, created_at DESC`.
- Group bookings by `hotel_id` in memory and pick top 3 for each hotel.

This turns 101 queries into just 2.
