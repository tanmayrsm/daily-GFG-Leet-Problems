# DB basics for booking/payments

## 1. ACID (booking/payment examples)

- **Atomicity**
  All steps in a transaction succeed or all fail.
  Example: "deduct wallet + create booking row" happens as one transaction. If booking insert fails, wallet deduction is rolled back; user is never charged without a booking.

- **Consistency**
  Every committed transaction keeps data valid under all constraints.
  Example: a constraint like "no two CONFIRMED bookings for same room and overlapping dates" ensures you never end up with two guests in the same room at the same time.

- **Isolation**
  Concurrent transactions behave as if they ran one by one (depending on isolation level).
  Example: two users trying to book the same last room don't both succeed—locks/constraints + isolation ensure only one transaction can confirm it.

- **Durability**
  Once a transaction commits, its data survives crashes.
  Example: after you mark `payment = SUCCESS` and `booking = CONFIRMED` and commit, a DB restart won't lose that booking; you won't have "money taken but booking disappeared".

---

## 2. Isolation levels and use cases

### READ COMMITTED

- Each statement sees committed data as of the moment that statement runs.
- A second SELECT may see newer data than the first.
- No dirty reads; non-repeatable reads and phantoms can happen.

**Use: Searching hotels**

- Users browsing hotel lists don't need a perfectly frozen snapshot.
- If availability or price changes between two queries, it's acceptable.
- You want higher throughput and fewer locks.

> Choice: **READ COMMITTED** for search APIs.

---

### REPEATABLE READ

- Transaction sees a stable snapshot for its whole duration.
- Repeated reads of the same rows return the same results.
- Prevents dirty and non-repeatable reads (phantoms depend on DB).

**Use: Finalizing a booking (option)**

- You might:
  - Read user, wallet, existing bookings,
  - Do multiple checks,
  - Then write booking + payment state.
- With REPEATABLE READ, those reads stay consistent while you work, simplifying reasoning.
- In practice, many systems use READ COMMITTED + explicit row locks and constraints (e.g., exclusion constraint on room/time) instead; both patterns are acceptable.

> Practical: **READ COMMITTED + row locks/constraints** is usually enough; **REPEATABLE READ** if you want a stronger "snapshot" mental model.

---

### SERIALIZABLE

- Strongest isolation: DB guarantees the outcome is as if transactions ran one after another.
- Prevents dirty reads, non-repeatable reads, and phantoms.
- Under high write load, some transactions may fail and must be retried.

**Use: Nightly revenue report**

- You want a consistent view of "all bookings and payments for yesterday".
- Run the report in:
  - REPEATABLE READ: take a snapshot at transaction start and use it for all reporting queries.
  - Or SERIALIZABLE: strongest guarantee, but you accept possible retries.

> Choice: **REPEATABLE READ or SERIALIZABLE** for long-running reporting jobs; **not** needed for normal search/booking flows.
