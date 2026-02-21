# Scaling, sharding, consistency – Agoda-style cheat sheet

## 1. Sharding / partitioning a huge bookings table

Table (simplified):
`booking(id, user_id, hotel_id, check_in, created_at, status, amount, …)`

### A) Partition by time (created_at / check_in)

- **Idea**: RANGE partitions per month/quarter/year.
- **Pros**:
  - Easy retention: drop old partitions.
  - "Last X days/months" queries hit a few partitions, not billions of rows.
- **Cons**:
  - All new writes hit the newest partition (hot partition).
  - "All bookings of user X" over several years still span many partitions.
- **Good for**:
  - Reporting & analytics.
  - Ops tasks like cleanup / GDPR retention.

---

### B) Shard by user_id (hash)

- **Idea**: `shard = hash(user_id) % N`.
- **Pros**:
  - All bookings for one user live on one shard → "My trips / booking history" is local.
  - Load balances naturally if user base is wide and active.
- **Cons**:
  - Hotel-centric queries (all bookings for hotel Y, utilization, overbooking checks) span many shards.
  - Inventory consistency can't rely on the bookings table alone; you'd need a separate, hotel-centric inventory service or table.
- **Good for**:
  - Strong per-user experience.
  - Notifications, user analytics.

---

### C) Shard by hotel_id (hash or region)

- **Idea**: each hotel (or group of hotels/region) belongs to one shard.
- **Pros**:
  - All bookings for one hotel are local:
    - Easier to enforce no double booking per room.
    - Hotel-level reporting and ops localized.
  - Fits well if inventory is enforced at DB level.
- **Cons**:
  - A user's bookings are spread across shards → "list my bookings" fans out or uses a separate index.
  - Very large hotels can be hot spots (need sub-sharding or special handling).
- **Good for**:
  - Inventory correctness.
  - Hotel owner tools, per-hotel analytics.

---

### D) Practical hybrid

- Primary sharding: **by hotel (or region)** for inventory and hotel ops.
- Within each shard: **partition by time** for manageability & retention.
- For user views: separate **user_booking_index** sharded by user_id, storing `user_id, booking_id, created_at` for fast "my bookings" queries.

---

## 2. Multi-datacenter setup for booking data

### Leader–follower pattern

- **Leader DC**:
  - All **writes** for bookings, payments, wallet.
  - Enforces strong constraints (no double booking/charge).
- **Follower DC(s)**:
  - Async replication from leader.
  - Serve read-heavy traffic (search results, booking history, reports).

### Read/write pattern

- **Writes**:
  - Go to leader region (booking confirmation, payment, wallet).
- **Reads**:
  - Fresh/critical reads (right after booking): leader or synchronous local replica.
  - Less critical reads: followers / read replicas.

### Failover

- If leader fails:
  - Promote a follower to leader (automated or manual).
  - Update routing / service discovery.
- Trade-off:
  - Synchronous cross-DC replication → strong consistency, higher latency, lower availability.
  - Asynchronous → lower latency, but temporary cross-DC inconsistencies.

---

## 3. Strong vs eventual consistency – what to choose

### a) Hotel inventory (availability)

- **Requirement**: never double-book a room.
- **Design**:
  - The **commit point** (booking confirmation / inventory decrement) should be **strongly consistent** in one place (leader DB or inventory service).
  - UI/search can show slightly stale availability; final booking step must re-check against strong source of truth.
- **Conclusion**:
  - **Strong consistency** at booking commit.
  - **Eventual consistency** acceptable for search / "X rooms left" indicators.

### b) User booking history

- **Requirement**: user should see correct list eventually; tiny delays are ok.
- **Design**:
  - Source-of-truth DB is strongly consistent.
  - Reading from replicas/caches can be eventually consistent (seconds behind).
- **Conclusion**:
  - Strong at origin; **eventual consistency** acceptable for read paths, as long as lag is small.

---

## 4. Read replica vs cache (Redis)

### Read replicas

- **What**: DB-level replication; almost-full copy of data.
- **Use when**:
  - Need to offload complex read queries (joins, filters) from primary.
  - Want more read throughput without changing app logic much.
  - Can tolerate replication lag.
- **Pros**:
  - Full SQL capability on replicas.
  - Great for reports, dashboards, search queries.
- **Cons**:
  - Still disk-based; slower than cache.
  - Lag under heavy write load.

### Cache (Redis)

- **What**: in-memory key-value store.
- **Use when**:
  - Same hot keys are read very frequently:
    - Hotel details by id,
    - Booking details after confirmation,
    - Top destinations list.
  - Want sub-millisecond responses.
- **Pros**:
  - Very fast; offloads both primary and replicas.
- **Cons**:
  - Limited memory; must choose what to cache.
  - Need invalidation/TTL logic.

### Heuristic

- Use **read replicas** for generic read scaling and complex queries.
- Use **Redis** for:
  - The hottest, most repetitive reads.
  - Precomputed or aggregated results (e.g., "top 10 destinations in Goa").

They're complementary, not either/or.

---

## 5. Migrating a large hot table to sharded design (minimal downtime)

High-level safe migration path:

1. **Introduce a routing layer**
   - A service / library that given a key (hotel_id or user_id) knows which DB/shard to query.
   - Initially, it routes everything to the old monolith DB.

2. **Create sharded schema**
   - Set up new shard DBs with the new `booking` schema.
   - Don't switch traffic yet.

3. **Dual writes (or mirror)**
   - For new bookings:
     - Either write to both:
       - Old monolith and new shards (dual-write), or
       - Preferably, write to shards and mirror to old DB for safety during migration.
   - Keep a clear "source of truth" flag per period or per user/hotel to avoid confusion.

4. **Backfill old data**
   - Background jobs copy historical bookings from monolith to shards in batches:
     - By time windows, or
     - By ID ranges.
   - Mark progress (e.g., "all rows up to date X migrated").

5. **Switch reads gradually**
   - For a subset of traffic (users, hotels, regions), route **reads** to shards:
     - If not found there, fall back to monolith.
   - Gradually ramp until all reads go to shards for the migrated segments.

6. **Switch writes**
   - Once confident:
     - Stop writing to monolith for those entities/time ranges.
     - Keep monolith read-only for a while as a safety fallback.
   - Eventually, decommission or archive the old table.

Key points:

- No "big bang"; run old and new in parallel for a while.
- Migration jobs are idempotent (safe to retry).
- Routing logic is explicit and feature-flagged so you can roll back if needed.

---

## 6. Handling Hot DB Shards Under High Traffic

### Detecting Hot Shards (Monitoring)

Track these **per shard / DB node**:

- CPU %, RAM, disk I/O, network.
- QPS (reads/writes), active connections.
- Query latency (P95/P99) and error rates (timeouts, deadlocks).
- Replication lag (if using replicas).

How to use it:

- Dashboards grouped by `shard_id / instance`.
- Alerts when:
  - CPU > X% for Y minutes,
  - P95 latency > threshold,
  - One shard's QPS or CPU is much higher than others.

---

### Immediate Mitigation When a Shard Is Hot

#### Step 1: Fix the biggest queries

- Look at slow-query / top-query views:
  - pg_stat_statements, slow logs, AWR, vendor tools, etc.
- Target:
  - Full table scans, missing indexes.
  - N+1 patterns, huge `IN` lists.
- Quick fixes:
  - Add/adjust indexes on hot columns.
  - Simplify queries, reduce selected columns, paginate.

This alone often drops load massively.

---

#### Step 2: Offload reads

- Add **read replicas** for that shard.
- Route:
  - Strongly consistent writes → primary.
  - Most reads / analytics / reports → replicas.

Plus:

- Add **caching** (Redis / app cache):
  - Cache hot rows / query results with short TTL.
  - Use cache-aside: app checks cache, falls back to DB on miss.

Goal: shrink read QPS hitting the hot shard.

---

#### Step 3: Tame writes

If writes are the problem:

- Batch writes where possible (e.g., bulk inserts, periodic flush).
- Move non-critical writes to async queues (Kafka, SQS, etc.).
- Drop unnecessary per-request writes (debug logs, counters) from the main DB.

---

#### Step 4: Scale the shard

Short term:

- Scale **vertically**: bigger instance (CPU/RAM/IOPS).

Long term:

- Scale **horizontally**:
  - Add more shards and move some data off the hot shard.
  - Example: if shard keyed by `user_id mod 4` and shard 0 is hot, go to mod 8 and migrate some users from 0 → 4.
- Use consistent hashing / routing to keep key → shard mapping stable during/after migration (but not per-request CPU based hopping).

---

### Ongoing Practices to Avoid Hot Shards

- **Good shard key**:
  - Avoid shard key that causes all hot users/tenants on the same shard (e.g., `country_id` if one country is 90% traffic).
  - Prefer high-cardinality, well-distributed keys (like user ID).

- **Rate limiting & backpressure**:
  - Per-tenant / per-endpoint rate limits so one noisy client doesn't burn a shard.
  - Queue overflow protection on write paths.

- **Regular review of top queries**:
  - Weekly slow-query / hot query review.
  - Use real traffic to guide indexing and schema changes.

---

### What *Not* to Do

- Don't send a key's traffic to a **different shard** just because current shard is busy:
  - That breaks data locality and consistency.
  - Use consistent hashing for **static partitioning**, not dynamic CPU-based routing.

Focus instead on:

1. Monitoring and alerting.
2. Query/index fixes.
3. Caching + read replicas.
4. Write smoothing.
5. Planned resharding when needed.
