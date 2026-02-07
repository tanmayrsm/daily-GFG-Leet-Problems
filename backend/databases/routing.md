# Routing – brief notes

## 1. What is "routing" here?

Routing = the logic in your app/gateway/DB client that decides **which database / region / replica** to send a request to.

It's *not* a DB feature by itself; it's code you write.

---

## 2. Types of routing and when they run

### a) Shard / region routing

- **What**: Map a logical key → specific DB/shard/region.
- **Examples**:
  - `hotel_id` → hotel shard (for bookings).
  - `user_id` → user shard (for user history).
- **When invoked**:
  - On every request that needs DB access:
    - "I have `hotel_id = 123`, so use shard 2 in EU."
    - "I have `user_id = 456`, so use shard 7."

---

### b) Primary vs replica routing (read vs write)

- **What**: Decide if query hits:
  - Primary (leader) DB, or
  - Read replica.
- **When invoked**:
  - On each DB call:
    - Writes (INSERT/UPDATE/DELETE) → primary.
    - Many SELECTs → replicas.
- **Use**:
  - Offload read traffic from primary while keeping writes on leader.

---

### c) "Read-your-writes" routing

- **What**: After a write, route that user's immediate reads to primary so they see fresh data (no replica lag issues).
- **When invoked**:
  - Right after a successful write (e.g., booking created).
  - For the next 1–2 requests or short time window, DB client forces reads to primary.
- **Example**:
  - User completes booking → "confirmation page" SELECT goes to primary, not replica, to guarantee they see the booking.
