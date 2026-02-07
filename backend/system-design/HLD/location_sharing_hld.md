# Location sharing use case (excluding row-level concurrency)

## 1. Core data model

- **user**
  - `user_id`, name, etc.

- **location_share_session**
  - `session_id` (PK)
  - `owner_user_id` (who is sharing)
  - `started_at`, `expires_at`
  - `visibility` (SHARED_WITH_USERS / GROUP / PUBLIC_LINK)

- **location_share_target**
  - `session_id` (FK → location_share_session)
  - `target_user_id`
  - `permissions` (e.g., view_only)

This models **"share with selected users"** via `location_share_target`.

---

## 2. Last known location vs history

- **user_last_location** (hot table)
  - `user_id` (PK)
  - `lat`, `lon`
  - `updated_at`
  - optional: `accuracy`, `source`

Use: very fast **"view last known location for user X"** by primary key lookup.

- **user_location_history** (time-series / audit)
  - `user_id`
  - `recorded_at`
  - `lat`, `lon`
  - optional: `session_id`, `device_id`, metadata

Use: recent path, analytics, debugging.

**Best practices:**

- Partition `user_location_history` by time (daily/weekly).
- Implement retention/TTL by **dropping old partitions** (e.g., keep 10 days, drop older).
- Index mainly on `(user_id, recorded_at)` (and optionally `(session_id, recorded_at)`), avoid over-indexing.

---

## 3. Data flow (high level)

1. Client sends location every X seconds to a Location service.
2. Location service:
   - Overwrites / upserts `user_last_location` for that user.
   - Appends to `user_location_history` for audit.
   - Optionally also writes to Elasticsearch index with a `geo_point` field for:
     - Nearby users,
     - Distance sort,
     - Map clustering.

---

## 4. Authorization for "view last known location"

When viewer V wants to see user U's location:

1. Check in DB:
   - An active `location_share_session` where `owner_user_id = U` and `expires_at > now()`.
2. Verify:
   - V appears in `location_share_target` for that session, or
   - Session visibility is GROUP/PUBLIC and V is allowed by group/link.
3. If allowed:
   - Read `user_last_location` for U and return that point.

This gives:

- Clear sharing rules ("who can see whom"),
- A small, hot "last location" table for fast reads,
- A partitioned history table with TTL for old data,
- Optional Elasticsearch layer for geo queries using `geo_point`.
