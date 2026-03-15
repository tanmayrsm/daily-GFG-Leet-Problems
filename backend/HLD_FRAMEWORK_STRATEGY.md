# 🏗️ HLD Framework: How to Ace the Interview

## The Problem

You have 45 minutes. The problem statement is vague. You need to design a system at scale.

**Most candidates fail because:**
- ❌ They jump to implementation without understanding requirements
- ❌ They miss key trade-offs
- ❌ Their design doesn't scale
- ❌ They can't explain *why* they made certain choices

**This guide fixes that.**

---

## ⏱️ Time Breakdown (45 minutes)

```
5 min:  Clarify requirements & constraints
5 min:  Back-of-envelope capacity estimation
10 min: High-level architecture (draw boxes)
15 min: Deep dive into 1-2 critical components
10 min: Handle failures & trade-offs
```

---

## Step 1: Clarify Requirements (5 min)

**Ask these FIRST. Do not skip.**

### Functional Requirements
- "What are the core features users need?"
- "Is this read-heavy or write-heavy?"
- "Do we need real-time updates?"

### Non-Functional Requirements
- "What's the expected number of users/QPS?"
- "What's the latency requirement?"
- "Is consistency or availability more important?"
- "Do we need to support offline?"
- "What's the scale of data?"

### Example Clarifications

**Uber:**
- 1M active drivers, 10M riders
- Driver location update every 2-3 seconds
- Match request must return in < 5 seconds
- Geographic distribution (US only vs global)

**Instagram:**
- 1B users, 500M daily active
- Heavy reads (feed), moderate writes (posts)
- Can be eventually consistent
- User can have up to 5M followers

**YouTube:**
- 2B users, 1B hours watched daily
- Upload heavy (500 hours/min), read heavier
- Global distribution critical
- Latency matters (streaming quality)

---

## Step 2: Capacity Planning (5 min)

### Formula
```
Users → DAU (Daily Active Users) → QPS → Servers Needed
```

### Example: Instagram Feed

**Given:**
- 1B total users
- 500M DAU (50% daily active)
- 50% of DAU request feed per day (250M feed requests)
- Peak traffic 2x average
- Feed request takes 500ms to respond

**Calculate:**
```
Feed requests per day = 250M
Feed requests per second = 250M / 86400 ≈ 2,900 QPS (average)
Peak QPS = 2,900 * 2 = 5,800 QPS

If each server handles 100 QPS:
Servers needed = 5,800 / 100 = 58 servers

With replication (3x) = 174 servers
With failover redundancy = 200+ servers
```

### Storage Calculation

**Example: Posts**
```
500M DAU
Average 1 post per day per active user = 500M posts/day
Each post = 1KB metadata + image reference = 5KB
500M posts/day * 5KB = 2.5TB/day
1 year = 900TB ≈ 1PB
```

**Store calculation:**
- Primary: 1PB
- Replication (3x): 3PB
- Backup: 3PB
- Total: 7PB (roughly)

---

## Step 3: High-Level Architecture (10 min)

### The "Boring" but Correct Approach

```
                    User Requests
                         ↓
                 ┌─────────────────┐
                 │ Load Balancer   │
                 │ (DNS + LB)      │
                 └────────┬────────┘
                          │
        ┌─────────────────┼─────────────────┐
        │                 │                 │
    ┌───▼───┐         ┌───▼───┐        ┌───▼───┐
    │ API 1 │         │ API 2 │        │ API 3 │
    │ (US)  │         │(APAC) │        │(Europe)
    └───┬───┘         └───┬───┘        └───┬───┘
        │                 │                 │
        └─────────────────┼─────────────────┘
                          │
                ┌─────────▼────────┐
                │ Cache Layer      │
                │ (Redis/Memcache) │
                └─────────┬────────┘
                          │
                ┌─────────▼────────┐
                │ Database Layer   │
                │ (Sharded DB)     │
                └──────────────────┘
```

### Key Components to Always Mention

1. **Load Balancer** → Distribute traffic
2. **API/Web Servers** → Stateless instances
3. **Cache Layer** → Hot data
4. **Database** → Persistent storage
5. **Search/Analytics** → Secondary access patterns
6. **Message Queue** → Async processing
7. **CDN** → Static assets / geo-distribution

### When to Use Each Component

| Component | When | Why |
|-----------|------|-----|
| **CDN** | Serving media/static files | Reduce latency globally |
| **Redis** | Hot data (users, sessions, rankings) | 100x faster than DB |
| **Kafka** | High-volume events, decoupling | Scale writes |
| **Elasticsearch** | Full-text search, analytics | Better search than DB |
| **S3/Blob Store** | Images, videos, large files | Cheap, durable storage |
| **Postgres** | Transactional, relational data | ACID guarantees |
| **NoSQL** | User data, activity logs | Horizontal scale |

---

## Step 4: Deep Dive (15 min)

### Pick 1-2 Components and Deep Dive

**IMPORTANT:** Don't try to design everything. Pick the **hardest** parts.

### A. Database Design (Most Common)

**Questions to Answer:**
1. What should be sharded? (by user_id, geolocation, etc.)
2. How do we handle consistency?
3. What if we need to reshard?

**Example: Instagram Feed (user-based sharding)**

```
Shard Key: user_id % 256

User 1 → Shard 1 → has all:
  - user profile
  - user posts
  - user followers
  - user feed

User 257 → Shard 2 → has all that user's data
```

**Schema:**
```sql
-- Users shard
CREATE TABLE users (
  id BIGINT PRIMARY KEY,
  username VARCHAR(255),
  profile_pic_url VARCHAR(255),
  created_at TIMESTAMP
);

-- Posts shard
CREATE TABLE posts (
  id BIGINT PRIMARY KEY,
  user_id BIGINT,
  content TEXT,
  created_at TIMESTAMP,
  INDEX (user_id, created_at)
);

-- Feed (denormalized for fast reads)
CREATE TABLE feed (
  user_id BIGINT,
  post_id BIGINT,
  author_id BIGINT,
  rank FLOAT,  -- For ranking
  created_at TIMESTAMP,
  PRIMARY KEY (user_id, rank DESC, created_at DESC)
);
```

**Advantages:**
✅ All user data on one shard (fan-out is local)
✅ Can enforce strong consistency per user
✅ Easy capacity planning

**Disadvantages:**
❌ Global queries (e.g., "trending posts") require fan-out to all shards
❌ Hot users can cause imbalance

### B. Caching Strategy

**Question:** What should we cache?

**Answer:** Follow the 80/20 rule. Cache the **hot 20% of data** that generates **80% of requests**.

```
1. Identify hot keys
   - Top 10K users generate 80% of feed requests
   - Top 100 hashtags generate 80% of searches

2. Choose cache strategy
   - Cache-Aside: App checks cache → misses → fetch DB → update cache
   - Write-Through: Write to cache AND DB synchronously
   - Write-Behind: Write to cache, async flush to DB (risky)

3. Cache invalidation
   - TTL: Expire after 5 minutes
   - Event-based: Invalidate on write
   - LRU: Remove least recently used items
```

**Example: Instagram Feed Caching**

```java
// Get user feed
Feed getHomeFeed(long userId) {
  String key = "feed:" + userId;

  // Try cache first
  List<Post> cachedFeed = redis.get(key, List.class);
  if (cachedFeed != null) {
    return cachedFeed;  // Hit!
  }

  // Cache miss: fetch from DB
  List<Post> dbFeed = database.getUserFeed(userId);

  // Store in cache with 5-minute TTL
  redis.set(key, dbFeed, 300);

  return dbFeed;
}

// When user posts, invalidate their followers' feeds
void onUserPost(long userId) {
  List<Long> followers = database.getFollowers(userId);

  for (long followerId : followers) {
    redis.delete("feed:" + followerId);  // Invalidate
  }
}
```

### C. Real-time Communication

**Choices:**
1. **Polling** (bad): Client asks server every N seconds
2. **WebSocket** (good): Persistent two-way connection
3. **SSE** (ok): One-way server push (not for Uber)

**Example: Uber Driver Location**

```
┌────────────────┐
│ Driver App     │
│ (WebSocket)    │
└────────┬───────┘
         │ sends location every 2 sec
         ↓
┌────────────────────────┐
│ WebSocket Gateway      │
│ (handles millions)      │
└────────┬───────────────┘
         │
         ↓ (fan-out)
┌────────────────────────┐
│ Kafka (log of updates) │
└────────┬───────────────┘
         │
         ↓
┌────────────────────────┐
│ Matching Service       │
│ subscribes to updates  │
└────────┬───────────────┘
         │
         ↓ (geohash index)
┌────────────────────────┐
│ Redis (geo-index)      │
│ get nearby drivers     │
└────────────────────────┘
```

---

## Step 5: Trade-offs & Failures (10 min)

### Always Discuss Trade-offs

**Format:** "I chose X because [benefit], but it means we sacrifice [downside]."

#### Example Trade-offs

**Strong Consistency vs Eventual Consistency**
```
Strong:  ✅ All reads see latest  ❌ Slower (wait for replicas)
Eventual: ✅ Faster writes        ❌ Temporary inconsistency

Instagram: Use eventual for feed (ok if delayed)
           Use strong for payments (must be accurate)
```

**Sharding Keys**
```
By User:      ✅ Fast user queries   ❌ Slow cross-user queries
By Time:      ✅ Fast date-range     ❌ Hot shard on current date
By Geography: ✅ Geo-aware routing   ❌ Uneven load if pop != uniform
```

### Failures to Handle

**Ask:** "What happens if [component] fails?"

1. **Cache failure** → Fall back to DB, degrade gracefully
2. **DB failure** → Read replicas, or manual failover
3. **Service failure** → Load balancer removes, auto-scaling kicks in
4. **Network partition** → Retry with backoff, circuit breaker
5. **Data loss** → Replication, backups, recovery procedures

**Example Response:**
```
Q: What if Redis crashes?

A: We handle it like this:
   1. Cache read fails → catch exception
   2. Check database immediately
   3. Refresh cache once it's back up

   This adds ~100ms latency but keeps system alive.
   We also have 3 Redis replicas for redundancy.
```

---

## 🎯 Common Mistakes to AVOID

### ❌ Mistake 1: No Capacity Planning
```
Bad:  "We'll use a database for everything"
Good: "Based on 5K QPS, we need 50 servers, with caching reducing load by 80%"
```

### ❌ Mistake 2: Forgetting the Client
```
Bad:  "Server sends data asynchronously"
Good: "Client polls every 2 seconds with exponential backoff"
```

### ❌ Mistake 3: Over-engineering
```
Bad:  "We'll build a distributed consensus layer with..."
Good: "For this scale, we only need Redis + DB replication"
```

### ❌ Mistake 4: No Data Model
```
Bad:  "We'll store user data somewhere"
Good: "Users table: id, name, email; indexed on email for auth"
```

### ❌ Mistake 5: Ignoring Consistency
```
Bad:  "We'll use eventual consistency everywhere"
Good: "Payments use strong consistency, feeds use eventual"
```

---

## 📋 Problem Patterns & Templates

### Pattern 1: Social Network (Instagram, Twitter, Facebook)

**Key Challenge:** Fanout
- When user posts, must update all followers' feeds
- **Solution 1 (Push):** Pre-compute feeds, store in cache
  - Good for: Small follower counts
  - Bad for: Celebrity with 10M followers
- **Solution 2 (Pull):** On-demand fetch from DB
  - Good for: Hot users can handle load
  - Bad for: Slow
- **Solution 3 (Hybrid):** Push for normal users, pull for celebrities
  - Best for: Most real systems

### Pattern 2: Real-time Systems (Uber, Doordash, Waze)

**Key Challenge:** Location updates at scale
- **Solution:** WebSocket + Kafka
  - Drivers send location every 2-3 sec via WebSocket
  - Gateway publishes to Kafka
  - Matching service subscribes, makes decisions
  - Use geo-hashing for spatial index

### Pattern 3: Messaging (WhatsApp, Slack)

**Key Challenge:** Message delivery guarantee + low latency
- **Solution:** Write-through + replicas
  - Client sends → stored in primary DB immediately
  - Ack sent to client
  - Asynchronously replicated
  - Receiver pulls from DB or real-time service

### Pattern 4: Video Streaming (Netflix, YouTube)

**Key Challenge:** Bandwidth + latency
- **Solution:** CDN + adaptive bitrate
  - Pre-encode videos at multiple bitrates
  - Distribute to CDNs globally
  - Client chooses bitrate based on connection speed
  - Cache at edge, expire old segments

### Pattern 5: Search (Google, Airbnb Search)

**Key Challenge:** Full-text search + filtering
- **Solution:** Elasticsearch
  - Index documents with Elasticsearch
  - Separate read path (ES) from write path (DB)
  - Cache top queries
  - Use filters (price, rating) as separate dimensions

---

## ✅ Pre-Interview Checklist

### Knowledge
- [ ] Know capacity planning formula (users → QPS → servers)
- [ ] Understand CAP theorem (can only pick 2 of 3)
- [ ] Know 3 sharding strategies and their trade-offs
- [ ] Can explain caching strategies (cache-aside, write-through, TTL)
- [ ] Understand replication (sync vs async)
- [ ] Know SQL (indexes, transactions, joins)
- [ ] Familiar with NoSQL (when to use, consistency models)

### Communication
- [ ] Always clarify requirements FIRST
- [ ] Ask about scale (DAU, QPS, data size)
- [ ] Explain trade-offs explicitly
- [ ] Draw architecture clearly
- [ ] Discuss failures and recovery
- [ ] Explain time/space complexity of your design

### Practice
- [ ] Done 5 full designs (45 min each)
- [ ] Can explain any design decision in 1-2 sentences
- [ ] Practiced speaking about trade-offs
- [ ] Know how to calculate capacity
- [ ] Can identify bottlenecks in a design

---

## 🚀 Final Tips

1. **Clarify first, design second.** Most candidates jump in too early.
2. **Draw boxes and lines.** Visual communication is powerful.
3. **Use numbers.** "5K QPS" beats "high scale."
4. **Discuss trade-offs.** Shows you think deeply.
5. **Ask "what if" questions** about failures.
6. **Go deep on 1-2 things** rather than shallow on everything.
7. **Explain your reasoning.** "Why" matters more than "what."

---

## 📚 Real Examples to Study

From your repo:
- `backend/system-design/HLD/agoda_room_book_backend_db_hld.md`
- `backend/system-design/HLD/whatsapp_live_location_hld.md`
- `backend/system-design/HLD/location_sharing_hld.md`
- `backend/databases/scaling.md` (sharding strategies)

**Study them and ask:** Why did they make that choice?

---

## 💪 You're Ready

You've got the foundation. Now practice the framework on real problems.

Good luck! 🚀
