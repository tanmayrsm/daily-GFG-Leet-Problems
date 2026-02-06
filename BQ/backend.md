## Concurrency challenge in production

- We hit an issue where an entity was updated in the database and the change was sent via Kafka, but some downstream flows still saw stale data for a short window.  
- Multiple downstream operations (running as separate threads) needed the latest state of that entity for the same request, and independent reads risked each thread observing a different version.  
- To fix this locally, one thread was responsible for reading the updated record from the database, storing it in a shared, thread‑safe reference, while the other threads waited; once the first read completed, all threads reused that same updated object.

## Ensuring correctness and ACID under load

- For hot update paths, we used row‑level locking (for example, `SELECT ... FOR UPDATE` / pessimistic locking) so concurrent updates to the same record were serialized and did not overwrite each other.  
- We partitioned data on meaningful business keys (such as user or merchant) to reduce contention on hot rows and to keep lock durations and wait times under control as throughput increased.  
- We validated this setup by monitoring lock wait times and high‑percentile latencies, ensuring we preserved ACID properties while still meeting performance requirements.

## Idempotency pattern for payments

- Each logical payment attempt carries a unique idempotency key from the client, and on the server we store the result keyed by this idempotency key plus a hash of core fields (payer, payee, amount, currency).  
- On the first request with a new key, we process the payment and persist the outcome; on any retry with the same key and identical payload, we simply return the stored result instead of calling the bank again, preventing double charges.  
- When a user wants to make a *new* payment to the same counterparty with a different amount, the client generates a new idempotency key, so the system can distinguish true retries (same key + same payload) from fresh payments (new key and/or payload).
