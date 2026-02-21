# Real-Time Architecture Guide: Leaderboards vs. Multi-Filter Search

## 1. Real-Time Competitive Programming Leaderboard

**Use Case:** Tracking ranks for thousands of concurrent users with sub-millisecond updates.

### High-Level Design (HLD)

1. **Submission Worker:** Validates the code. Once a "Correct" status is achieved, it calculates the points.
2. **The Hot Path:** Points are sent directly to **Redis**. We don't wait for the Oracle DB to update before showing the rank to the user.
3. **The Cold Path:** Points are also sent to **Oracle** for permanent record (Audit/History).
4. **Scaling:** Redis handles the heavy lifting. Even with 100k users, the O(log N) complexity of the Skip List ensures that the leaderboard never lags.

### Architecture Flow

```
[User Submits Code]
       |
       v
[Submission Worker]
       |
       +---> [Validates & Scores]
       |
   +---+----------------------------+
   |                                |
   v (Hot Path)                     v (Cold Path)
[Redis ZADD/ZINCRBY]          [Oracle INSERT/UPDATE]
   |                                |
   v                                v
[Real-time Leaderboard]       [Audit/History Tables]
   |
   v
[User sees rank instantly]
```

### Why Redis Sorted Sets (ZSET)?

**Data Structure:** Uses a **Skip List**. Imagine a linked list with "express lanes" that allow you to jump ahead, making searches and insertions extremely fast.

```
Level 3:  1 -----------------> 50 ----------------> 100
Level 2:  1 -----> 20 -----> 50 -----> 75 -----> 100
Level 1:  1 -> 10 -> 20 -> 30 -> 50 -> 60 -> 75 -> 85 -> 100
Level 0:  1 -> 5 -> 10 -> 15 -> 20 -> ... (all nodes)
```

**Atomicity:** `ZINCRBY` is atomic. If two submissions land at the exact same millisecond, Redis handles them sequentially, preventing race conditions.

### Key Commands

```redis
# Add user with score
ZADD leaderboard:contest_123 850 "user_456"

# Increment score atomically
ZINCRBY leaderboard:contest_123 50 "user_456"

# Get user's rank (0-indexed)
ZREVRANK leaderboard:contest_123 "user_456"

# Get top 10
ZREVRANGE leaderboard:contest_123 0 9 WITHSCORES

# Get user's score
ZSCORE leaderboard:contest_123 "user_456"

# Count users above certain score
ZCOUNT leaderboard:contest_123 900 +inf
```

### Performance Characteristics

| Operation | Time Complexity | Use Case |
|-----------|----------------|----------|
| ZADD | O(log N) | Add new user to leaderboard |
| ZINCRBY | O(log N) | Update user's score |
| ZRANK/ZREVRANK | O(log N) | Get user's current rank |
| ZRANGE | O(log N + M) | Get top M users |
| ZSCORE | O(1) | Get user's score |

Even with 1 million users, log₂(1M) ≈ 20 operations max.

### Handling Ties

```redis
# For users with same score, Redis uses lexicographic order on member name
# To handle ties by timestamp, encode it in the member:
ZADD leaderboard 850 "user_456:1739012345"
```

---

## 2. Real-Time Multi-Filter Search (Agoda Style)

**Use Case:** Filtering millions of hotels by location, price, amenities, and rating.

### High-Level Design (HLD)

1. **Source of Truth:** All hotel data (rooms, pricing) lives in **Oracle/PostgreSQL**.
2. **Streaming Bridge (CDC):** **Debezium** watches Oracle's Redo Logs. If a hotel changes its price, Debezium immediately emits an event.
3. **Message Bus:** **Kafka** holds these events. This decouples Oracle from the search engine.
4. **Sink/Mapper:** **Kafka Connect** (the Mapper) transforms the relational data into a JSON document and pushes it to **Elasticsearch**.
5. **Querying:** The user's front-end talks to a Search API that queries Elasticsearch.

### Architecture Flow

```
[Oracle DB: Hotel Data]
       |
       | (Redo Logs)
       v
[Debezium CDC]
       |
       v
[Kafka Topic: hotel-changes]
       |
       v
[Kafka Connect Sink + SMT]
       | (Transform & Map)
       v
[Elasticsearch: hotel index]
       |
       v
[Search API]
       |
       v
[User gets filtered results]
```

### Why Elasticsearch (ES)?

**Inverted Index:** Instead of searching row by row, ES has a map of "Words/Filters" to "Document IDs."

Example: It has a list of all Hotel IDs that have "Free Breakfast." It simply intersects that list with the "Price < 5000" list.

```
Inverted Index Structure:

amenity:"free_breakfast" → [hotel_1, hotel_5, hotel_7, hotel_23, ...]
price_range:0-5000 → [hotel_1, hotel_3, hotel_5, hotel_9, ...]
city:"chennai" → [hotel_1, hotel_2, hotel_5, hotel_8, ...]

Query: "Hotels in Chennai with Free Breakfast under 5000"
Result: Intersection of above 3 lists → [hotel_1, hotel_5]
```

**Horizontal Scaling:** As Agoda grows, you just add more ES nodes. It re-shards the data automatically.

### Sample ES Query

```json
GET /hotels/_search
{
  "query": {
    "bool": {
      "must": [
        { "match": { "city": "chennai" } },
        { "range": { "price": { "lte": 5000 } } }
      ],
      "filter": [
        { "term": { "amenities": "free_breakfast" } },
        { "range": { "rating": { "gte": 4.0 } } }
      ]
    }
  },
  "sort": [
    { "rating": "desc" },
    { "price": "asc" }
  ],
  "size": 20
}
```

---

## 3. Data Synchronization: The "How-To"

This section details how we keep the "Source of Truth" (Oracle) in sync with the "Search/Rank" layers.

### Log-Based CDC vs. Batch Polling

| Feature | Logstash Batch (Rundeck) | Debezium CDC (Real-Time) |
| --- | --- | --- |
| **Logic Location** | Inside Logstash `.conf` (Filter block). | Inside Kafka SMTs or Stream Processor. |
| **Update Trigger** | Clock-based (e.g., every 1 hour). | Event-based (as it happens). |
| **Delete Handling** | Requires "Soft Deletes" in SQL. | Automatically detects `DELETE` from logs. |
| **DB Impact** | High (Heavy `SELECT` queries). | Negligible (Reads log files). |
| **Latency** | Minutes to hours | Milliseconds to seconds |
| **Complexity** | Simple to set up | Requires CDC infrastructure |
| **Cost** | Lower (just scheduled jobs) | Higher (Kafka, Debezium cluster) |

### The Mapping Layer (The "Brain")

In your career as a Senior Dev, you'll choose the mapper based on complexity:

**Simple Mapping:** Use **SMTs** (Single Message Transforms) inside Kafka Connect. It's declarative JSON. Great for renaming `PROD_ID` to `id`.

```json
{
  "name": "hotel-sink",
  "config": {
    "connector.class": "io.confluent.connect.elasticsearch.ElasticsearchSinkConnector",
    "transforms": "RenameField,ExtractValue",
    "transforms.RenameField.type": "org.apache.kafka.connect.transforms.ReplaceField$Value",
    "transforms.RenameField.renames": "HOTEL_ID:id,HOTEL_NAME:name"
  }
}
```

**Complex Mapping:** Use **Logstash** (subscribing to Kafka) or a **Java Microservice**. Best when you need to call an external API or perform heavy math during the sync.

```java
@KafkaListener(topics = "hotel-changes")
public void processHotelChange(HotelChangeEvent event) {
    // Complex transformation
    HotelDocument doc = new HotelDocument();
    doc.setId(event.getHotelId());
    doc.setName(event.getName());

    // Call external API for coordinates
    Coordinates coords = geocodingService.getCoordinates(event.getAddress());
    doc.setLocation(coords);

    // Calculate derived fields
    doc.setAveragePrice(priceCalculator.calculateAverage(event.getHotelId()));

    // Index to Elasticsearch
    elasticsearchClient.index(doc);
}
```

---

## 4. CDC with Debezium: Deep Dive

### What is CDC (Change Data Capture)?

CDC reads database transaction logs (not the tables themselves) and emits events for every INSERT, UPDATE, DELETE.

### Debezium Architecture

```
[Oracle Redo Logs]
       |
       v
[Debezium Oracle Connector]
       | (Reads LogMiner API)
       v
[Kafka Topic: dbserver1.SCHEMA.HOTEL]
       |
       v (Events: create, update, delete)
[
  {
    "op": "u",
    "before": { "id": 123, "price": 5000 },
    "after": { "id": 123, "price": 4500 },
    "ts_ms": 1739012345678
  }
]
```

### Event Structure

```json
{
  "op": "u",                    // Operation: c=create, u=update, d=delete, r=read(snapshot)
  "before": {                   // Previous state (null for inserts)
    "HOTEL_ID": 123,
    "NAME": "Marriott Chennai",
    "PRICE": 5000
  },
  "after": {                    // New state (null for deletes)
    "HOTEL_ID": 123,
    "NAME": "Marriott Chennai",
    "PRICE": 4500
  },
  "source": {
    "version": "1.9.0",
    "connector": "oracle",
    "name": "dbserver1",
    "ts_ms": 1739012345678,
    "db": "ORCL",
    "schema": "HOTEL_SCHEMA",
    "table": "HOTEL"
  },
  "ts_ms": 1739012345678
}
```

### Handling Deletes

**Problem:** When a hotel is deleted from Oracle, how does ES know to remove it?

**Solution 1: Tombstone Events**
```json
{
  "op": "d",
  "before": { "HOTEL_ID": 123, ... },
  "after": null                   // null indicates deletion
}
```

Sink connector sees `after: null` and deletes the document from ES.

**Solution 2: Soft Deletes**
```sql
-- Instead of DELETE
UPDATE hotel SET deleted_at = NOW() WHERE hotel_id = 123;
```

Debezium sees UPDATE, sends event, ES marks document as inactive or filters it out.

---

## 5. Performance Comparison

### Leaderboard: Redis vs. Database

| Metric | Redis ZSET | PostgreSQL | Oracle |
|--------|------------|------------|--------|
| Add/Update Score | O(log N), ~1ms | O(log N), ~10-50ms | O(log N), ~20-100ms |
| Get Rank | O(log N), <1ms | O(N), 100ms-1s | O(N), 200ms-2s |
| Get Top 100 | O(log N), <1ms | O(N log N), 50-500ms | O(N log N), 100-1000ms |
| Concurrent Writes | 100k+ ops/sec | 1k-10k ops/sec | 1k-5k ops/sec |

**Verdict:** For real-time leaderboards, Redis is 10-100x faster.

### Search: Elasticsearch vs. Database

| Query Type | Elasticsearch | PostgreSQL (Indexed) | Oracle (Indexed) |
|------------|---------------|---------------------|------------------|
| Single filter (price) | ~10ms | ~20ms | ~30ms |
| 3-4 filters (AND) | ~20ms | ~100-500ms | ~200-1000ms |
| Full-text search | ~20ms | ~1-5s (tsvector) | ~2-10s |
| Fuzzy/typo-tolerant | ~30ms | Not practical | Not practical |
| Geo-distance | ~20ms | ~100-500ms (PostGIS) | Not practical |
| Faceted search | ~30ms | ~500ms-5s | ~1-10s |

**Verdict:** ES is 10-100x faster for complex multi-filter searches.

---

## 6. Summary for Interviews

**For pure ranking:**
> "Redis is unbeatable due to RAM speed and the ZSET data structure. I'd use Redis Sorted Sets for leaderboards, with O(log N) operations for add/rank/top queries. The hot path writes to Redis immediately for sub-millisecond updates, while a cold path asynchronously persists to the source DB for audit."

**For discovery/filters:**
> "Elasticsearch is the standard because of its Inverted Index and ability to handle fuzzy or multi-attribute search. I'd use Debezium CDC to stream changes from the source DB to Kafka, then use Kafka Connect to sync to ES. This keeps ES eventually consistent with the source of truth without dual writes."

**For consistency:**
> "Avoid dual writes in application code—they cause inconsistencies. Instead, use CDC (Debezium) to ensure your secondary stores (Redis/ES) always reflect the truth in your primary DB. The source DB is the single source of truth; everything else is derived."

**Architecture Pattern:**
```
Source of Truth (PostgreSQL/Oracle)
       ↓
    CDC (Debezium)
       ↓
    Kafka (Message Bus)
       ↓
  ┌────┴────┐
  ↓         ↓
Redis    Elasticsearch
(Rank)    (Search)
  ↓         ↓
User sees results
```

---

## 7. Trade-offs and When NOT to Use This Pattern

### Don't use Redis ZSET when:
- You need complex queries beyond rank/score (use ES or DB)
- You need transactions across multiple keys (use DB)
- Data size exceeds memory capacity (use DB with pagination)
- You need strong durability guarantees (Redis can lose data on crash without AOF)

### Don't use Elasticsearch when:
- Simple key-value lookups (use Redis or DB)
- Strong consistency required (use DB)
- Complex joins across many tables (use DB)
- Exact aggregations for financial data (use DB)

### Don't use CDC when:
- Source DB doesn't support it (use batch sync or API-driven updates)
- Latency requirements are relaxed (hourly batch is simpler)
- Very small scale (dual writes might be acceptable)
- Team doesn't have CDC expertise (simpler alternatives first)

---

## 8. Real-World Gotchas

### Redis Leaderboard
- **Tie-breaking:** Use composite scores (score + timestamp) or member suffixes
- **Memory limits:** Use Redis maxmemory-policy to evict old leaderboards
- **Persistence:** Enable AOF for durability, but accept some perf cost
- **Sharding:** If leaderboard > 1M users, consider sharding by region/category

### Elasticsearch Sync
- **Mapping changes:** ES doesn't support changing field types—requires reindex
- **Backfilling:** Initial snapshot can take hours/days for large datasets
- **Schema evolution:** Plan for backward-compatible changes or version indices
- **Lag monitoring:** CDC lag can spike; monitor Kafka consumer lag

### Debezium CDC
- **Database load:** LogMiner/redo log reading can impact DB performance
- **Network bandwidth:** Large updates generate large events
- **Schema changes:** DDL (ALTER TABLE) can break CDC connectors
- **Permissions:** Requires special DB permissions (LogMiner, replication slots)
