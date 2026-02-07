# SQL vs NoSQL, and related patterns

## 1. SQL vs NoSQL for different workloads

### Session storage

- **Best**: NoSQL key–value (Redis, DynamoDB, etc.).
- Why:
  - Very high read/write throughput.
  - Simple access pattern: `session_id → blob`.
  - TTL/expiry built-in, loss is tolerable in worst case.

### Search logs (queries, filters)

- **Option 1 (moderate scale)**: SQL (Postgres) with time-based partitioning.
  - Easy SQL analytics: top queries, error rates.
- **Option 2 (large scale)**: streaming + NoSQL/log store.
  - E.g., Kafka → Elasticsearch / data lake for search/analytics.

### Real-time clickstream

- **Best**: streaming + NoSQL / data lake.
  - Kafka/Kinesis → stream processor → columnar store, time-series DB, or Elasticsearch.
- Focus is on analytics and ML, not single row lookups.

### Bookings

- **Best**: SQL (Postgres/MySQL/Yugabyte…).
- Why:
  - ACID needed for "wallet + booking + inventory".
  - Constraints & joins (no double booking, foreign keys).
- NoSQL plays around it (cache, search), not as source of truth.

---

## 2. When to use document store / key–value in Agoda-like stack

### Document store (MongoDB, Couchbase, etc.)

Use when data is naturally JSON, variable per user, and not ultra-critical for strong relational constraints.

Examples:

- **User preferences**:
  - `user_id` + JSON with language, currency, notification settings, etc.
- **Feature flag definitions** (if not using a dedicated service):
  - Flag name, conditions, rollout rules as a document.
- **Saved searches / complex filters**:
  - Nested filters, sort options, etc.

### Key–value store (Redis / similar)

Use when access is "get/set by key" and you need speed:

- **User sessions**:
  - `session_id → { user_id, device, last_seen }`.
- **Runtime feature flags**:
  - `flag_key → config` for quick reads by many services.
- **Pricing snapshots / availability cache**:
  - `hotel:{id}:date_range → precomputed price/availability` with TTL.

---

## 3. Schema evolution in a document DB (backward compatible)

Goals: **don't break old documents**, **let new code read old data**.

Key techniques:

1. **Add, don't break**
   - Only add new optional fields.
   - Avoid renaming/removing fields in a way that breaks old docs.

2. **Versioning**
   - Add `schema_version` to documents.
   - New code:
     - Reads `schema_version`.
     - Handles older versions (e.g., uses defaults, upgrades in memory).

3. **Tolerant readers**
   - Code must:
     - Handle missing fields (use default).
     - Ignore unknown extra fields.

4. **Background or lazy migration**
   - For big changes:
     - New writes use new schema.
     - Old docs migrated slowly in batches or on read.
   - Keep code able to read both versions until migration is done.

Example (user preferences):

- Old: `{ "theme": "dark" }`
- New: `{ "theme": "dark", "currency": "INR", "schema_version": 2 }`
- Reader:
  - If `currency` missing → default "INR".
  - If `schema_version < 2` → treat as older format.

---

## 4. NoSQL sessions cluster under pressure – diagnose & fix

Imagine a NoSQL cluster (e.g., Redis, DynamoDB, Cassandra) storing user sessions is struggling under traffic.

### A) Diagnose

1. **Cluster health**
   - CPU, memory, network, disk per node.
   - Is one node more loaded than others? (possible hot partition).

2. **Hot keys / partition skew**
   - Are a few keys or partitions getting most traffic?
   - Bad partition key choice can cause this (e.g., too many sessions under same hash).

3. **Access pattern**
   - Are you:
     - Updating sessions on every tiny event?
     - Doing scans / secondary index queries instead of key lookups?
     - Storing huge blobs in sessions?

4. **Client behavior**
   - Are clients using aggressive timeouts and causing retry storms?
   - Spikes in retries can overload cluster more than the original traffic.

### B) Fix model / patterns

1. **Fix key design**
   - Use well-distributed keys (e.g., `session_id`, not "user:all_sessions").
   - Avoid patterns that concentrate many hot users/objects in one partition.

2. **Slim down session payload**
   - Only keep what you really need in session.
   - Move analytics or large objects elsewhere.

3. **Reduce write frequency**
   - Don't update the session on every click.
   - Batch updates or update only on meaningful events (login, checkout step, etc.).

4. **Tune timeouts and retries**
   - Increase timeouts moderately.
   - Use exponential backoff / jitter to avoid synchronized retry storms.

5. **Scale out**
   - After fixing data model and access:
     - Add nodes/partitions.
     - Increase replicas for read-heavy workloads.

---

## Interview Summary

> "I'd keep bookings in SQL for ACID guarantees, use key–value NoSQL for sessions and hot pricing snapshots, and a document store for flexible user preferences. For schema evolution, I'd use additive changes and versioned documents so new code can still read old data. If a NoSQL sessions cluster is under pressure, I'd first look for bad partitioning or hot keys and overly chatty access patterns, fix those, then scale out and tune retries, rather than just throwing hardware at the problem."
