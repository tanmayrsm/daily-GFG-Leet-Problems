# Redis as Distributed Cache

## What is a Distributed Cache?

- A distributed cache stores data across multiple nodes so all service instances can share the same cached values, unlike local in‑memory caches tied to one process.
- Redis is the most common choice for distributed caching in microservices architectures.

---

## When to Use Redis

Redis works well for:

- **Caching hot DB query results and reference data** to reduce latency and database load.
- **Storing idempotency keys and payment/notification status** so retries can quickly read consistent state.
- **Managing sessions, tokens, and cross‑service shared data** in microservices architectures.

---

## Common Use Cases in Agoda-Style Systems

### 1. Database Query Cache

```
Key: hotel:123:details
Value: {hotel_id: 123, name: "Marriott Chennai", rating: 4.5, ...}
TTL: 300 seconds
```

- Reduces load on primary database
- Sub-millisecond reads for hot hotels

### 2. Session Storage

```
Key: session:abc123xyz
Value: {user_id: 456, device: "mobile", last_seen: ...}
TTL: 1800 seconds (30 minutes)
```

- Shared across all API servers
- Fast session validation

### 3. Idempotency Keys

```
Key: payment:user_123:booking_456
Value: {status: "SUCCESS", transaction_id: "txn_789", timestamp: ...}
TTL: 86400 seconds (24 hours)
```

- Prevent double payments on retries
- Quick lookup without DB hit

### 4. Rate Limiting Counters

```
Key: ratelimit:user_789:search
Value: 45 (request count)
TTL: 60 seconds
```

- Track requests per time window
- Atomic increment operations

### 5. Availability Snapshots

```
Key: availability:hotel_123:2026-02-10:2026-02-12
Value: {rooms_available: 5, last_updated: ...}
TTL: 60 seconds
```

- Cache expensive availability calculations
- Reduce load during high-traffic searches

---

## Important Concerns

### Cache Invalidation

- **Use TTLs** to ensure data doesn't stay stale indefinitely
- **Explicit invalidation** on updates:
  ```
  - When hotel details change → delete cache key
  - When booking confirmed → invalidate availability cache
  ```
- **Write-through vs write-behind**:
  - Write-through: update DB and cache together
  - Write-behind: update cache first, async write to DB (risky for critical data)

### High Availability

- **Replication**: Master-replica setup
  - Master handles writes
  - Replicas serve reads
- **Clustering**: Redis Cluster for horizontal scaling
  - Data sharded across multiple nodes
  - Automatic failover
- **Persistence**: RDB snapshots or AOF for durability
  - Choose based on acceptable data loss window

### Cache Stampede Prevention

Problem: When cache expires, many requests hit DB simultaneously.

Solutions:
1. **Probabilistic early expiration**: Refresh cache slightly before TTL
2. **Lock on cache miss**: First request rebuilds cache, others wait
3. **Background refresh**: Update cache before expiration

---

## Local Cache vs Distributed Cache

### Local Cache (in-process)

- **Pros**: Fastest (no network), simple
- **Cons**: Not shared across instances, memory per instance

### Distributed Cache (Redis)

- **Pros**: Shared state, centralized invalidation
- **Cons**: Network latency, additional infrastructure

### Hybrid Approach

- **L1**: Local in-memory cache (100ms TTL)
- **L2**: Redis distributed cache (5min TTL)
- **L3**: Database

Flow:
1. Check L1 → if hit, return
2. Check L2 → if hit, populate L1 and return
3. Query DB → populate L2, L1, and return

---

## Redis Best Practices for Booking Systems

1. **Use appropriate data structures**:
   - Strings for simple values
   - Hashes for objects (hotel details)
   - Sets for unique collections (user's active bookings)
   - Sorted sets for leaderboards/rankings

2. **Set reasonable TTLs**:
   - Reference data (hotel details): 5-10 minutes
   - Availability: 30-60 seconds
   - Sessions: 30 minutes with sliding window
   - Idempotency keys: 24 hours

3. **Monitor memory usage**:
   - Use `maxmemory-policy` (e.g., `allkeys-lru`)
   - Evict least recently used keys when memory is full

4. **Use pipelining for bulk operations**:
   - Batch multiple commands to reduce round-trips

5. **Consider Redis Cluster for scale**:
   - When single instance can't handle traffic
   - Automatic sharding and replication
