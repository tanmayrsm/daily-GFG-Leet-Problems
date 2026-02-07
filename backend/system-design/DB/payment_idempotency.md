# Payment idempotency and wallet transactions

## 1. "Deduct from wallet + create booking" as one unit

Goal: either both happen, or neither; and retries must not double-charge.

### 1.1 One transaction (ACID)

Inside a single DB transaction:

1. Read wallet row for user.
2. Check `balance >= amount`.
3. Deduct amount from wallet (update balance).
4. Create booking row (or mark existing booking as CONFIRMED).
5. Commit.

If any step fails (e.g., insufficient balance, constraint violation), the entire transaction is rolled back:

- No partial wallet deduction.
- No booking without corresponding wallet update.

That's **Atomicity + Consistency** in action, with Isolation ensuring concurrent deductions/booking attempts don't corrupt state.

---

## 2. Avoiding double charging (idempotency use case)

Isolation and ACID protect you *inside* one transaction.
To handle retries (network errors, user hitting "Pay" again), you need **idempotency**.

### 2.1 Idempotency concept

- Operation is idempotent if doing it once or many times has the same final effect.
- For payments: the same logical "pay for booking X" should charge at most once, even if the API is called multiple times due to retries/timeouts.

### 2.2 Idempotency key

- Client or upstream service sends a unique **idempotency key** per logical payment:
  - Example: `payment_key = "user:{userId}:booking:{bookingId}"` or just a UUID.
- Payment service:
  - Stores this key in a table with a **unique constraint**.
  - On first request with that key:
    - Insert record with status `IN_PROGRESS`.
    - Call payment provider (or perform wallet deduction + booking transaction).
    - Update status to `SUCCESS` or `FAILED`.
  - On subsequent requests with the same key:
    - Look up record:
      - If `SUCCESS`: return the same success result, do **not** re-charge.
      - If terminal `FAILED`: return failure, do **not** re-charge.
      - If `IN_PROGRESS`: optionally say "still processing".

This ensures:

- No matter how many times the same request (same key) is retried, the user is charged at most once, and you don't create duplicate bookings tied to that key.

### 2.3 How this fits with isolation levels

- Isolation level (READ COMMITTED / REPEATABLE READ / SERIALIZABLE) governs **how concurrent transactions see and affect rows**.
- Idempotency key governs **how repeated calls over time** map to a **single logical payment**.
- For the "wallet + booking" flow:
  - Use an appropriate isolation level (often READ COMMITTED + locks/constraints) to keep one transaction internally consistent.
  - Wrap the external payment / wallet operation with an idempotency key so retries don't produce multiple charges for the same booking.

Together you get:

- Correct single-execution behavior (ACID + isolation).
- Safe behavior under retries and network issues (idempotency).
