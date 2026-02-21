# Concurrency and Locking in PostgreSQL

## 1. Row-level read concurrency (MVCC)

### What it means

- Many transactions can read the **same row at the same time**.
- Readers don't block each other, and they don't block writers.
- Only writers to the **same row** contend with each other.

### How Postgres does it (MVCC)

- Every update creates a **new version** of the row, old version is kept for existing readers.
- Each transaction sees a **snapshot** of data (only the versions valid for it).
- Result:
  - Readers see consistent data, even while writes happen.
  - Writers only block other writers on that row, not readers.

### When to use explicit row locks

- Default reads just use MVCC (no explicit locks).
- When you need to "reserve" a row to update it safely (job queue, inventory, balances), you:
  - Read the row **and** take a row lock.
  - Other writers to that row wait or skip it until you finish.

---

## 2. Optimistic vs pessimistic locking – core idea

- **Optimistic locking**
  - Assumes conflicts are rare.
  - Many transactions can read and attempt to update the same logical row.
  - On update, you **check if the row changed** since you read it (via `version` / `updated_at`).
  - If changed → conflict → your code retries or fails.

- **Pessimistic locking**
  - Assumes conflicts are likely or too expensive to fix after the fact.
  - When you start working with a row, you **lock it**.
  - Others trying to update/lock the same row must **wait**, or **skip/fail** depending on the lock mode.
  - DB manages the waiting and locking.

---

## 3. Optimistic locking – pattern + Agoda-ish example

### Pattern

Add a `version` (or `updated_at`) column:

```text
promo(id, remaining_quota, version)
```

Flow:

1. Read promo: `remaining_quota = 50, version = 10`.
2. Compute: `new_remaining = 49`.
3. Try to update with a version check:

   - `UPDATE promo SET remaining_quota = 49, version = 11 WHERE id = :id AND version = 10;`

4. If:
   - Rows updated = 1 → success, you "won" the race.
   - Rows updated = 0 → someone else updated first → conflict.

Your **application** handles conflict:

- Re-read row, see new `remaining_quota`.
- If still > 0, optionally retry once.
- If 0, respond "promo exhausted".

### When this is okay

- Conflicts are **not very frequent**.
- Conflict handling is **cheap**:
  - You haven't sent payment or committed external side effects yet.
  - You can safely re-check and retry or say "no more promo".

**Agoda-like use**:

- **Promo with large quota** (e.g., 10,000 uses):
  - Updates are spread out.
  - A few retries are acceptable.
  - Optimistic locking avoids lots of waiting and lock overhead.

---

## 4. Pessimistic locking – pattern + Agoda-ish examples

### Basic row lock

Use a `SELECT ... FOR UPDATE` inside a transaction:

```sql
BEGIN;

SELECT *
FROM promo
WHERE id = :id
FOR UPDATE;  -- DB locks this row for this tx

-- do checks, then

UPDATE promo
SET remaining_quota = remaining_quota - 1
WHERE id = :id;

COMMIT;
```

- `FOR UPDATE` asks the DB to put a **row-level lock** on this promo.
- Other transactions trying to lock/update this row:
  - **Wait** until you commit/rollback, or
  - Fail immediately if you used a NOWAIT variant.

The DB's lock manager handles:

- Keeping track of who holds the lock.
- Making others wait or error.

### Queue-style pessimistic (no waiting: SKIP LOCKED)

For job queues, you often use:

```sql
SELECT id
FROM tasks
WHERE status = 'PENDING'
ORDER BY created_at
FOR UPDATE SKIP LOCKED
LIMIT 100;
```

- Each worker:
  - Locks a batch of rows.
  - Others **skip** already-locked jobs; they don't wait.
- Still pessimistic locks (rows are locked), but workers don't block each other; they just pick different jobs.

### When pessimistic is better

- Conflicts are **likely** (hot rows).
- Conflict is **expensive** to retry (e.g., money movement, external API calls).
- You want strict "one at a time" behavior on that row.

**Agoda-like uses**:

- **Flash sale / limited promo quota (small remaining, huge traffic)**:
  - Only 50 uses left, thousands of requests.
  - If you use optimistic, many conflicts and retries; may tangle with payments.
  - Pessimistic:
    - Lock promo row while checking quota and decrementing.
    - Others wait or get a fast "deal over" result.
- **Inventory / "only 1 room left" scenarios**:
  - Lock the inventory/room row while you reserve it.
  - Prevent multiple bookings racing for the last unit.

---

## 5. How to phrase it in interviews (Agoda context)

You can summarize like this:

- **Optimistic locking**:
  > "I add a `version` column and update with `WHERE version = old_version`. If 0 rows are affected, I know someone else updated first and I reload and retry or fail. This works well when conflicts are rare and it's cheap to retry, like a high-quota promo where a few users collide occasionally."

- **Pessimistic locking**:
  > "For hot rows, like a flash promo with very small remaining quota or the last room in a hotel, I use pessimistic locking via `SELECT ... FOR UPDATE`. That locks the row so only one transaction can decrement quota or reserve at a time; others wait or are rejected. For job queues I'd use `FOR UPDATE SKIP LOCKED` so workers claim different rows without blocking."

Key idea: optimistic = **detect and retry**, pessimistic = **lock and serialize**.

---

## 6. Promo Service: Optimistic vs Pessimistic (Practical Guide)

### Overall stance

- Promo/discount service is **mostly optimistic**.
- Use **pessimistic / atomic counters** only on a few true hotspots (limited-use promos).

---

### Where to use optimistic locking

Use a `version` column and `UPDATE ... WHERE version = ?`:

**User-promo usage**
- "Has this user already used PROMO_X?"
- Update row with `version` check; if rows affected = 0 → someone else changed it.

```sql
UPDATE user_promo_usage
SET used = true, version = version + 1
WHERE user_id = :user_id
  AND promo_id = :promo_id
  AND version = :current_version
  AND used = false;
```

**Promo config (rules, validity)**
- Marketing edits promo rules rarely, so optimistic versioning avoids overwrites.

```sql
UPDATE promo
SET discount_percent = :new_discount,
    version = version + 1
WHERE promo_id = :promo_id
  AND version = :current_version;
```

**Per-booking promo application**
- Store a `version` or promo snapshot on booking; verify on commit to avoid applying outdated rules.

Conflicts are rare → optimistic is cheap and scales.

---

### Where to use pessimistic / atomic

For Agoda-style **limited inventory promos**:

- Example: "First 100 bookings get 80% off."

Use **atomic counter** on a promo inventory row:

```sql
UPDATE promo_inventory
SET remaining = remaining - 1
WHERE promo_id = :promo_id
  AND remaining > 0;
```

- If affected rows = 1 → you got a slot.
- If 0 → sold out.

If you must read more state then update, you might wrap that row with `SELECT ... FOR UPDATE` (pessimistic) in a short transaction:

```sql
BEGIN;

SELECT remaining
FROM promo_inventory
WHERE promo_id = :promo_id
FOR UPDATE;

-- Check conditions, then:

UPDATE promo_inventory
SET remaining = remaining - 1
WHERE promo_id = :promo_id;

COMMIT;
```

---

### Idempotency around the APIs

**Apply promo API**
- Client sends `Idempotency-Key`.
- Server:
  - If key seen → return same result.
  - Else:
    - Run DB logic (optimistic/pessimistic as above).
    - Store result keyed by the idempotency key.

```java
public PromoApplyResult applyPromo(String userId, String promoId, String idempotencyKey) {
    // Check idempotency store (Redis)
    PromoApplyResult cached = redis.get("promo:apply:" + idempotencyKey);
    if (cached != null) {
        return cached; // Already processed
    }

    // Process with appropriate locking
    PromoApplyResult result = processPromoApplication(userId, promoId);

    // Store result
    redis.setex("promo:apply:" + idempotencyKey, 86400, result);

    return result;
}
```

**Issue promo / coupons**
- Use idempotent issue calls so retries don't create duplicate coupons.

Idempotency = protects against **retries**.
Locking (optimistic/pessimistic) = protects against **concurrent writes**.

---

### Agoda-style mapping (Summary)

| Use Case | Strategy | Reason |
|----------|----------|--------|
| Promo definitions | Optimistic | Rare updates, version conflicts easy to handle |
| User-usage tracking | Optimistic | Low contention per user |
| Booking promo application | Optimistic | Different bookings, low conflict |
| Limited-count inventory ("only 100 uses") | Atomic/Pessimistic | High contention, atomic decrement required |
| All write APIs | Idempotent | Protect against retries |

**Decision tree:**
- Low contention + cheap retry? → **Optimistic**
- High contention + expensive retry? → **Pessimistic**
- API level? → Always **Idempotent**
