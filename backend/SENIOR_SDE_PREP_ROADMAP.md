# 🎯 Senior SDE Backend: LLD + HLD Mastery Roadmap

## 📊 Current Status (Your Repo Analysis)

**Strong Areas:**
✅ Java Concurrency (threading, producer-consumer)
✅ Database fundamentals (Postgres, NoSQL, transactions)
✅ HLD examples (Agoda, WhatsApp, Location Sharing)
✅ Some LLD (Rate Limiter, Container Orchestrator)
✅ Caching, scaling, sharding concepts

**Gaps to Fill:**
⚠️ More diverse LLD patterns
⚠️ Real code implementations for every design
⚠️ System design trade-off clarity
⚠️ Resilience patterns in depth
⚠️ Distributed systems thinking

---

# 🔴 PART 1: LLD (Low-Level Design) — Code at Scale

## What LLD Tests
- **Can you write scalable, maintainable code?**
- Do you know design patterns and when to use them?
- Can you handle concurrency, threading, and synchronization?
- Do you think about extensibility, testability, SOLID?
- Can you implement real services (Uber, Zomato, etc.) in code?

---

## 1️⃣ SOLID Principles + Design Patterns

### **S - Single Responsibility Principle**
Each class has ONE reason to change.

**Example: Payment Service**
```java
// ❌ BAD: Multiple responsibilities
class PaymentProcessor {
  void processPayment() { /* charge */ }
  void sendEmail() { /* notify */ }
  void logToDatabase() { /* audit */ }
  void updateWallet() { /* balance */ }
}

// ✅ GOOD: Separated concerns
class PaymentProcessor {
  private ChargeService charger;
  private NotificationService notifier;
  private AuditService auditor;

  void processPayment(Payment p) {
    charger.charge(p);
    notifier.notify(p.userId);
    auditor.log(p);
  }
}
```

### **O - Open/Closed Principle**
Open for extension, closed for modification.

**Example: Payment Gateway**
```java
// ❌ BAD: Can't add new payment types without modifying
class PaymentGateway {
  Payment processPayment(PaymentType type) {
    if (type == CREDIT_CARD) return processCC();
    else if (type == UPI) return processUPI();
    else if (type == WALLET) return processWallet();
    // Adding new type = modify this class!
  }
}

// ✅ GOOD: Extensible design
interface PaymentProcessor {
  Payment process(PaymentDetails details);
}

class CreditCardProcessor implements PaymentProcessor { }
class UPIProcessor implements PaymentProcessor { }
class WalletProcessor implements PaymentProcessor { }

class PaymentGateway {
  Map<PaymentType, PaymentProcessor> processors;

  Payment processPayment(PaymentType type, PaymentDetails details) {
    return processors.get(type).process(details);
  }
}
```

### **L - Liskov Substitution Principle**
Subtypes must be substitutable for their base type.

```java
// ❌ BAD: Square breaks contract of Rectangle
class Rectangle {
  int width, height;
  void setWidth(int w) { this.width = w; }
  void setHeight(int h) { this.height = h; }
  int area() { return width * height; }
}

class Square extends Rectangle {
  @Override
  void setWidth(int w) { width = height = w; } // Violates contract!
}

// ✅ GOOD: Use composition or redesign
interface Shape {
  int area();
}

class Rectangle implements Shape {
  int width, height;
  public int area() { return width * height; }
}

class Square implements Shape {
  int side;
  public int area() { return side * side; }
}
```

### **I - Interface Segregation Principle**
Clients shouldn't depend on interfaces they don't use.

```java
// ❌ BAD: Force all animals to implement swim()
interface Animal {
  void eat();
  void sleep();
  void swim();
}

class Dog implements Animal {
  public void swim() { } // Dogs don't need to swim!
}

// ✅ GOOD: Segregate interfaces
interface Eatable { void eat(); }
interface Sleepable { void sleep(); }
interface Swimmable { void swim(); }

class Dog implements Eatable, Sleepable { }
class Duck implements Eatable, Sleepable, Swimmable { }
```

### **D - Dependency Inversion Principle**
Depend on abstractions, not concretions.

```java
// ❌ BAD: Hard dependency on concrete class
class PaymentService {
  private StripePaymentGateway stripe = new StripePaymentGateway();

  void pay(int amount) {
    stripe.charge(amount); // Tight coupling!
  }
}

// ✅ GOOD: Depend on abstraction
class PaymentService {
  private PaymentGateway gateway;

  PaymentService(PaymentGateway gateway) {
    this.gateway = gateway; // Injected dependency
  }

  void pay(int amount) {
    gateway.charge(amount);
  }
}
```

---

## 2️⃣ Essential Design Patterns for Backend

### **Strategy Pattern** (most common in interviews)
Encapsulate algorithms in separate classes.

```java
// Problem: Different pricing strategies (regular, seasonal, surge)
interface PricingStrategy {
  double calculatePrice(Order order);
}

class RegularPricing implements PricingStrategy {
  public double calculatePrice(Order o) { return o.basePrice; }
}

class SurgePrice implements PricingStrategy {
  public double calculatePrice(Order o) { return o.basePrice * 1.5; }
}

class PricingEngine {
  private PricingStrategy strategy;

  PricingEngine(PricingStrategy strategy) { this.strategy = strategy; }

  double getPrice(Order order) {
    return strategy.calculatePrice(order);
  }
}

// Usage
Order order = new Order(100);
PricingEngine engine = new PricingEngine(new SurgePrice());
System.out.println(engine.getPrice(order)); // 150
```

### **Factory Pattern**
Decouple object creation from usage.

```java
// Problem: Create different notification types
interface Notification {
  void send(String message);
}

class EmailNotification implements Notification {
  public void send(String msg) { /* send email */ }
}

class SMSNotification implements Notification {
  public void send(String msg) { /* send SMS */ }
}

class NotificationFactory {
  static Notification createNotification(NotificationType type) {
    return switch(type) {
      case EMAIL -> new EmailNotification();
      case SMS -> new SMSNotification();
      case PUSH -> new PushNotification();
    };
  }
}
```

### **Builder Pattern**
Construct complex objects step-by-step.

```java
// Problem: Order has too many optional parameters
class Order {
  int id;
  String userId;
  List<Item> items;
  String address;
  String notes;
  boolean express;

  // Avoid telescoping constructors!
  Order(int id, String userId, List<Item> items, String address,
        String notes, boolean express) { }
}

// Solution: Builder
class Order {
  final int id;
  final String userId;
  final List<Item> items;
  final String address;
  final String notes;
  final boolean express;

  private Order(Builder builder) {
    this.id = builder.id;
    this.userId = builder.userId;
    this.items = builder.items;
    this.address = builder.address;
    this.notes = builder.notes;
    this.express = builder.express;
  }

  static class Builder {
    int id;
    String userId;
    List<Item> items = new ArrayList<>();
    String address;
    String notes = "";
    boolean express = false;

    Builder id(int id) { this.id = id; return this; }
    Builder userId(String userId) { this.userId = userId; return this; }
    Builder items(List<Item> items) { this.items = items; return this; }
    Builder address(String address) { this.address = address; return this; }
    Builder notes(String notes) { this.notes = notes; return this; }
    Builder express(boolean express) { this.express = express; return this; }

    Order build() {
      if (id == 0 || userId == null) throw new IllegalArgumentException("Required fields");
      return new Order(this);
    }
  }
}

// Usage
Order order = new Order.Builder()
  .id(1)
  .userId("user123")
  .items(Arrays.asList(item1, item2))
  .address("123 Main St")
  .express(true)
  .build();
```

### **Observer Pattern**
Notify multiple subscribers of state changes.

```java
interface Observer {
  void update(Event event);
}

class OrderStatusListener implements Observer {
  public void update(Event event) {
    System.out.println("Order status: " + event.status);
  }
}

class OrderSubject {
  private List<Observer> observers = new ArrayList<>();

  void subscribe(Observer obs) { observers.add(obs); }
  void unsubscribe(Observer obs) { observers.remove(obs); }

  void notifyObservers(Event event) {
    for (Observer obs : observers) {
      obs.update(event);
    }
  }

  void updateOrderStatus(String status) {
    notifyObservers(new Event(status));
  }
}
```

### **Decorator Pattern**
Add behavior to objects dynamically.

```java
interface DataProcessor {
  String process(String data);
}

class BasicProcessor implements DataProcessor {
  public String process(String data) { return data; }
}

abstract class ProcessorDecorator implements DataProcessor {
  protected DataProcessor processor;
  ProcessorDecorator(DataProcessor p) { this.processor = p; }
}

class CompressionDecorator extends ProcessorDecorator {
  CompressionDecorator(DataProcessor p) { super(p); }

  public String process(String data) {
    String compressed = compressData(processor.process(data));
    return compressed;
  }

  private String compressData(String data) { /* gzip */ return data; }
}

class EncryptionDecorator extends ProcessorDecorator {
  EncryptionDecorator(DataProcessor p) { super(p); }

  public String process(String data) {
    String encrypted = encryptData(processor.process(data));
    return encrypted;
  }

  private String encryptData(String data) { /* AES */ return data; }
}

// Usage: Chain decorators
DataProcessor processor = new EncryptionDecorator(
  new CompressionDecorator(
    new BasicProcessor()
  )
);
String result = processor.process("secret"); // Compressed + Encrypted
```

### **Singleton Pattern** (with thread safety)
Ensure only one instance exists.

```java
// ❌ BAD: Not thread-safe
class Database {
  private static Database instance;
  private Database() { }

  static Database getInstance() {
    if (instance == null) {
      instance = new Database();
    }
    return instance;
  }
}

// ✅ GOOD: Thread-safe lazy initialization
class Database {
  private Database() { }

  static class Holder {
    static Database INSTANCE = new Database();
  }

  static Database getInstance() {
    return Holder.INSTANCE;
  }
}

// Or use enum (most elegant)
enum Database {
  INSTANCE;

  Database() { /* initialize */ }
}
```

---

## 3️⃣ Concurrency in Depth

### **Thread Safety Patterns**

**Pattern 1: Synchronized blocks (coarse-grained)**
```java
class BankAccount {
  private int balance = 0;

  synchronized void deposit(int amount) {
    balance += amount;
  }

  synchronized int getBalance() {
    return balance;
  }
}
```

**Pattern 2: Fine-grained locking with ReadWriteLock**
```java
class Cache<K, V> {
  private Map<K, V> data = new HashMap<>();
  private ReadWriteLock lock = new ReentrantReadWriteLock();

  V get(K key) {
    lock.readLock().lock();
    try {
      return data.get(key);
    } finally {
      lock.readLock().unlock();
    }
  }

  void put(K key, V value) {
    lock.writeLock().lock();
    try {
      data.put(key, value);
    } finally {
      lock.writeLock().unlock();
    }
  }
}
```

**Pattern 3: Compare-and-swap (lock-free)**
```java
import java.util.concurrent.atomic.AtomicInteger;

class Counter {
  private AtomicInteger count = new AtomicInteger(0);

  void increment() {
    count.incrementAndGet(); // No locks!
  }

  int getValue() {
    return count.get();
  }
}
```

### **Producer-Consumer Pattern**
```java
class BoundedQueue<T> {
  private Queue<T> queue = new LinkedList<>();
  private final int capacity;
  private Lock lock = new ReentrantLock();
  private Condition notFull = lock.newCondition();
  private Condition notEmpty = lock.newCondition();

  BoundedQueue(int capacity) { this.capacity = capacity; }

  void produce(T item) throws InterruptedException {
    lock.lock();
    try {
      while (queue.size() >= capacity) {
        notFull.await(); // Wait if full
      }
      queue.offer(item);
      notEmpty.signalAll(); // Wake up consumers
    } finally {
      lock.unlock();
    }
  }

  T consume() throws InterruptedException {
    lock.lock();
    try {
      while (queue.isEmpty()) {
        notEmpty.await(); // Wait if empty
      }
      T item = queue.poll();
      notFull.signalAll(); // Wake up producers
      return item;
    } finally {
      lock.unlock();
    }
  }
}
```

---

## 4️⃣ Real LLD Interview Problems (Practice These!)

### **Problem 1: Design a Rate Limiter**
See: `backend/system-design/LLD/rate_limiter_lld.md`

Key patterns:
- Token Bucket vs Sliding Window
- Thread-safe counter with locks
- Time-based reset logic

### **Problem 2: Design a Cache (LRU/LFU)**
```java
class LRUCache<K, V> {
  final int capacity;
  Map<K, V> cache = new LinkedHashMap<K, V>(capacity, 0.75f, true) {
    protected boolean removeEldestEntry(Map.Entry eldest) {
      return size() > capacity;
    }
  };

  V get(K key) {
    return cache.get(key); // LinkedHashMap tracks access order
  }

  void put(K key, V value) {
    cache.put(key, value);
  }
}
```

### **Problem 3: Design a Task Scheduler**
```java
interface Task extends Runnable {
  long getPriority();
}

class TaskScheduler {
  PriorityQueue<Task> queue;
  ExecutorService executor = Executors.newFixedThreadPool(5);

  void schedule(Task task) {
    queue.offer(task);
  }

  void start() {
    while (true) {
      Task next = queue.poll();
      if (next != null) {
        executor.execute(next);
      }
    }
  }
}
```

### **Problem 4: Design a Parking Lot System**
```java
class ParkingLot {
  List<Floor> floors;

  Ticket park(Vehicle v) {
    for (Floor floor : floors) {
      Spot spot = floor.findAvailableSpot(v.size);
      if (spot != null) {
        spot.park(v);
        return new Ticket(v.id, spot.id, System.currentTimeMillis());
      }
    }
    return null; // Full
  }

  void unpark(Ticket ticket) {
    Spot spot = findSpot(ticket.spotId);
    long duration = System.currentTimeMillis() - ticket.entryTime;
    double fee = calculateFee(duration);
    spot.unpark();
    System.out.println("Fee: " + fee);
  }
}
```

### **Problem 5: Design a Hotel Booking System**
```java
class HotelBookingSystem {
  Map<Integer, Room> rooms = new HashMap<>();
  List<Booking> bookings = new ArrayList<>();

  synchronized Booking bookRoom(int roomId, LocalDate start, LocalDate end, User user) {
    Room room = rooms.get(roomId);

    if (isAvailable(roomId, start, end)) {
      Booking booking = new Booking(roomId, start, end, user);
      bookings.add(booking);
      return booking;
    }
    return null;
  }

  private boolean isAvailable(int roomId, LocalDate start, LocalDate end) {
    return bookings.stream()
      .filter(b -> b.roomId == roomId)
      .noneMatch(b -> !(b.endDate.isBefore(start) || b.startDate.isAfter(end)));
  }
}
```

---

# 🟠 PART 2: HLD (High-Level Design) — Systems at Scale

## What HLD Tests
- **Can you design for scale?** (Millions of users, billions of events)
- Do you understand trade-offs? (Consistency vs availability, latency vs durability)
- Can you handle failures gracefully? (Resilience patterns)
- Do you know microservices architecture?
- Can you design data models for scale? (Sharding, replication, consistency)

---

## 1️⃣ Core HLD Concepts

### **Load Balancing**
Distribute traffic across multiple servers.

```
┌─────────────────────────────────────┐
│         Load Balancer               │
│  (Round Robin / Least Connections)  │
└────────────┬────────────┬───────────┘
             │            │
        ┌────▼───┐   ┌────▼───┐
        │ Server1 │   │ Server2 │
        └─────────┘   └─────────┘
```

Strategies:
- Round Robin: Simple but doesn't consider load
- Least Connections: Send to server with fewest active connections
- Weighted Round Robin: Some servers handle more traffic
- IP Hash: Sticky sessions (same user → same server)
- Health Check: Remove failed servers automatically

### **Caching Layers**
Multiple cache levels for different access patterns.

```
User Request
    ↓
┌─────────────────────┐
│  CDN Cache (Edge)   │  ← Fastest, global
└──────────┬──────────┘
    Miss   │
           ↓
┌─────────────────────┐
│  Redis/MemCache     │  ← Session, hot data
└──────────┬──────────┘
    Miss   │
           ↓
┌─────────────────────┐
│  Database Cache     │  ← Query results
└──────────┬──────────┘
    Miss   │
           ↓
┌─────────────────────┐
│  Database           │  ← Source of truth
└─────────────────────┘
```

Cache Strategies:
- **Cache-Aside**: App checks cache, falls back to DB
- **Write-Through**: Write to cache + DB synchronously
- **Write-Behind**: Write to cache, async write to DB (risk: loss if crash)
- **TTL-Based**: Expire cache after N seconds

### **Sharding (Horizontal Partitioning)**
Split data across multiple databases.

```
Users table (1B rows)
├── Shard 0: user_id % 4 == 0 (250M rows, small DB)
├── Shard 1: user_id % 4 == 1 (250M rows, small DB)
├── Shard 2: user_id % 4 == 2 (250M rows, small DB)
└── Shard 3: user_id % 4 == 3 (250M rows, small DB)
```

Sharding strategies:
- **Range-based**: user_id 1-250M → shard0, 250M-500M → shard1
- **Hash-based**: hash(user_id) % 4
- **Directory-based**: Lookup table (slow but flexible)
- **Geo-based**: US users → shard0, EU → shard1

**Problem: Resharding**
If you need more shards (4 → 8), you must redistribute data. Plan for this!

### **Replication**
Keep copies of data for reliability.

```
Leader (receives writes)
    ↓
    ├→ Replica1 (read-only)
    ├→ Replica2 (read-only)
    └→ Replica3 (read-only)
```

Patterns:
- **Synchronous**: Write ack only after all replicas confirm (slow but consistent)
- **Asynchronous**: Write ack immediately, replicate later (fast but risky)
- **Semi-sync**: Write ack after 1+ replicas confirm (balance)

### **Consistency Guarantees**

| Level | Definition | Latency | Use Case |
|-------|-----------|---------|----------|
| **Strong** | All reads see latest write | High | Payment, inventory |
| **Eventual** | All reads see write after delay | Low | Social feed, likes |
| **Causal** | Causally-related reads ordered | Medium | Comments on post |
| **Weak** | No guarantee | Lowest | Analytics, logs |

### **Database Transactions (ACID)**

| Property | Meaning | Implementation |
|----------|---------|-----------------|
| **Atomicity** | All or nothing | Rollback on failure |
| **Consistency** | Data validity | Constraints + triggers |
| **Isolation** | No dirty reads | Locks + snapshots |
| **Durability** | Survive crashes | Write-ahead logs, fsync |

Isolation Levels (from weak to strong):
1. **Read Uncommitted**: Can read uncommitted changes (dirty reads)
2. **Read Committed**: Only read committed data (no dirty reads)
3. **Repeatable Read**: No phantom reads within transaction
4. **Serializable**: Complete isolation (slowest)

---

## 2️⃣ Resilience Patterns

### **Retry Logic**
```java
class RetryPolicy {
  static <T> T executeWithRetry(
    Callable<T> task,
    int maxRetries,
    long initialDelayMs
  ) throws Exception {
    long delay = initialDelayMs;

    for (int attempt = 0; attempt < maxRetries; attempt++) {
      try {
        return task.call();
      } catch (RetryableException e) {
        if (attempt == maxRetries - 1) throw e;

        Thread.sleep(delay);
        delay *= 2; // Exponential backoff
      }
    }
    return null;
  }
}
```

### **Circuit Breaker**
Fail fast when downstream service is down.

```java
enum CircuitState { CLOSED, OPEN, HALF_OPEN }

class CircuitBreaker {
  CircuitState state = CLOSED;
  int failureCount = 0;
  long lastFailureTime = 0;
  final int failureThreshold = 5;
  final long timeoutMs = 60_000;

  Object execute(Supplier<Object> supplier) throws Exception {
    if (state == OPEN) {
      if (System.currentTimeMillis() - lastFailureTime > timeoutMs) {
        state = HALF_OPEN; // Try recovery
      } else {
        throw new CircuitBreakerOpenException();
      }
    }

    try {
      Object result = supplier.get();

      if (state == HALF_OPEN) {
        state = CLOSED; // Recovered!
        failureCount = 0;
      }
      return result;
    } catch (Exception e) {
      failureCount++;
      lastFailureTime = System.currentTimeMillis();

      if (failureCount >= failureThreshold) {
        state = OPEN; // Trip the breaker
      }
      throw e;
    }
  }
}
```

### **Bulkhead Pattern**
Isolate resources to prevent cascading failures.

```java
ExecutorService userServicePool = Executors.newFixedThreadPool(10);
ExecutorService paymentServicePool = Executors.newFixedThreadPool(5);

// If payment pool exhausts, user service still works
userServicePool.execute(() -> getUserData());
paymentServicePool.execute(() -> processPayment());
```

### **Timeout**
```java
CompletableFuture<Response> future =
  CompletableFuture.supplyAsync(() -> callSlowService());

try {
  Response response = future.get(5, TimeUnit.SECONDS);
} catch (TimeoutException e) {
  System.out.println("Service timeout!");
}
```

---

## 3️⃣ Real HLD Problems to Practice

### **Problem 1: Design YouTube/Netflix (Video Streaming)**

**Architecture:**
```
┌─────────────────────────────────────────────┐
│             CDN (Edge Caching)              │
│  Videos cached at edge locations globally   │
└────────────────┬────────────────────────────┘
                 │
        ┌────────▼────────┐
        │ Load Balancer   │
        └────────┬────────┘
                 │
    ┌────────┬───┴───┬─────────┐
    │        │       │         │
┌───▼──┐ ┌──▼──┐ ┌──▼──┐ ┌───▼──┐
│ API1 │ │ API2│ │ API3│ │ API4 │
└───┬──┘ └──┬──┘ └──┬──┘ └───┬──┘
    │       │      │        │
    └───┬───┴──┬───┴────────┘
        │      │
    ┌───▼──┐  ┌──▼────────┐
    │Redis │  │ Postgres  │
    │Cache │  │ Database  │
    └──────┘  └───┬──────┘
                  │
            ┌─────▼──────┐
            │ S3 (Videos)│
            └────────────┘
```

**Key Decisions:**
- **Video Encoding**: Convert to multiple resolutions/bitrates (adaptive streaming)
- **CDN Strategy**: Pre-cache popular videos at edge
- **Metadata**: User watch history in Redis (fast reads)
- **Comments**: Separate service (can be slow, eventually consistent)
- **Recommendations**: ML model, precomputed, served from cache

### **Problem 2: Design Uber (Real-time Matching)**

**Architecture:**
```
Driver Location Updates (every 2 sec)
    ↓
┌─────────────────────┐
│ WebSocket Gateway   │
│ (millions of sockets)│
└────────────┬────────┘
             │
     ┌───────▼────────┐
     │ Kafka (pub/sub) │  Stream millions of location updates
     └───────┬────────┘
             │
     ┌───────▼────────────┐
     │ Real-time Analytics│  Matching service
     │ (Geo-indexed)      │  subscribes to this
     └──────────┬─────────┘
                │
        ┌───────▼────────────┐
        │ Geohashing Engine  │  Fast geo-queries
        │ (Redis GeoIndex)   │
        └──────────┬─────────┘
                   │
            ┌──────▼──────┐
            │ Match Engine│  Find nearest drivers
            └──────┬──────┘
                   │
        ┌──────────▼──────────┐
        │ Notification Service│  Send to driver
        └─────────────────────┘
```

**Key Decisions:**
- **Location Updates**: WebSocket (not REST) for low latency
- **Geo-Indexing**: Geohashing in Redis for fast neighbor queries
- **Matching**: Rule engine (driver rating, distance, availability)
- **Consistency**: Eventually consistent (fast matching over strong consistency)
- **Surge Pricing**: Based on demand ratio, precomputed

### **Problem 3: Design Instagram Feed**

**Architecture:**
```
User Timeline Request
    ↓
┌─────────────────────┐
│ Feed Service        │
└────────────┬────────┘
             │
    ┌────────▼────────┐
    │ Redis Timeline  │  Cached feeds (LRU)
    │ 10K most active │
    │ users per shard │
    └────────┬────────┘
       Miss  │  Hit
       ┌─────┴─────┐
       │           │
   ┌───▼───┐   ┌──▼──────────┐
   │ Fanout│   │ Return from  │
   │Service│   │ Cache        │
   └───┬───┘   └──────────────┘
       │
   ┌───▼────────────────┐
   │ Post DB (sharded)  │
   │ Merge friend posts │
   └──────────┬─────────┘
              │
       ┌──────▼──────┐
       │ Add to cache│
       │ with TTL    │
       └─────────────┘
```

**Key Decisions:**
- **Feed Generation**: Pull-based (on demand) vs push-based (precomputed)
  - Pull: For each user, fetch all friend posts → slow
  - Push: When user posts, add to all followers' feeds → fast reads, slow writes
  - Hybrid: Push to active users, pull for inactive
- **Ranking**: Engagement score (likes, comments, recency)
- **Caching**: LRU cache for top 10K users (80/20 rule)
- **Sharding**: User-based sharding (all user A's data on shard0)

### **Problem 4: Design Payment System**

**Critical: Exactly-once processing**

```
Payment Request
    ↓
┌──────────────────────┐
│ Idempotency Check    │  Check if already processed
│ (Using unique key)   │
└────────────┬─────────┘
             │
    ┌────────▼──────────┐
    │ Generate UUID     │
    │ Store: uuid→state │
    └────────┬──────────┘
             │
    ┌────────▼──────────┐
    │ Process Payment   │
    │ (Charge card)     │
    └────────┬──────────┘
             │
    ┌────────▼──────────┐
    │ Update State:     │
    │ PENDING→SUCCESS   │
    │ (Atomic)          │
    └────────┬──────────┘
             │
    ┌────────▼──────────┐
    │ Emit Event        │  For other services
    │ (payment.success) │
    └──────────────────┘
```

**Key Patterns:**
- **Idempotency Key**: Client sends UUID, server checks if already processed
- **State Machine**: PENDING → SUCCESS/FAILED
- **Reconciliation**: Regular checks for stale transactions
- **Saga Pattern**: Distributed transactions (order → payment → shipping)

---

## 4️⃣ System Design Interview Strategy

### **Framework (45 min)**

**5 min: Clarification**
- DAU/QPS/Throughput?
- Consistency vs Availability?
- Read/write ratio?
- Data volume?
- Latency requirements?

**5 min: Capacity Planning**
- If 1M DAU, 10% active → 100K QPS
- Each server handles 1K QPS → need 100 servers
- Add replication + failover → 150 servers

**10 min: High-level design**
- Draw architecture (load balancer → services → DB)
- Identify bottlenecks
- Propose solutions (cache, sharding, async)

**15 min: Deep dive (pick 1-2 components)**
- How does cache invalidation work?
- How do you shard the database?
- What if a service fails?
- How do you handle consistency?

**10 min: Trade-offs & scaling**
- Why Redis over Memcached?
- Why eventual consistency?
- What happens at 100M users?

---

# 🎯 PRACTICE PLAN (8 Weeks to Senior)

## **Week 1-2: LLD Fundamentals**
- [ ] Master all SOLID principles with code
- [ ] Learn 5 design patterns deeply (Strategy, Factory, Builder, Observer, Singleton)
- [ ] Implement: LRU Cache, Rate Limiter, Task Scheduler
- [ ] Review your existing code for SOLID violations

## **Week 3-4: Concurrency Mastery**
- [ ] Deep dive: ReentrantLock, ReadWriteLock, StampedLock
- [ ] Implement: Thread-safe Cache with ReadWriteLock
- [ ] Implement: Custom Thread Pool with rejection policies
- [ ] Implement: Producer-Consumer with Blocking Queue
- [ ] Problem: Hotel Booking System (concurrent reservations)

## **Week 5: HLD Foundations**
- [ ] Capacity planning (calculating QPS, storage)
- [ ] Understand: Caching, sharding, replication, consistency
- [ ] Learn: CAP theorem and trade-offs
- [ ] Read: Your existing HLD designs (Agoda, Location Sharing, WhatsApp)

## **Week 6-7: Full System Designs**
- [ ] Design: YouTube (video streaming at scale)
- [ ] Design: Uber (real-time matching)
- [ ] Design: Instagram Feed (pull vs push trade-offs)
- [ ] Design: Payment System (idempotency, saga pattern)
- [ ] Time each design: 45 min max

## **Week 8: Refinement & Weak Spots**
- [ ] Redo designs from memory
- [ ] Practice explaining trade-offs clearly
- [ ] Mock interviews (45 min, timed)
- [ ] Ask: "What if X fails?" for every design

---

# 📚 Additional Resources to Study

## **Books**
1. **Designing Data-Intensive Applications** (Martin Kleppmann)
   - Best for understanding distributed systems fundamentals
   - Must read: Chapters 1-8 (partitioning, replication, transactions)

2. **System Design Interview** (Alex Xu)
   - Practical examples, 16 real problems
   - Best for interview preparation

3. **Refactoring** (Martin Fowler)
   - SOLID principles in action
   - Code smell identification

## **Online Resources**
- **ByteByteGo** (YouTube) — System design explained simply
- **Grokking the System Design Interview** (Educative) — Interactive problems
- **LeetCode Premium** — System design problems with solutions

---

# ✅ Checklist Before Interview

## LLD
- [ ] Can write 3 design patterns from memory with good code
- [ ] Understand SOLID and can identify violations
- [ ] Can implement LRU/LFU cache in 20 min
- [ ] Know ThreadPool, ExecutorService API well
- [ ] Understand concurrency primitives deeply

## HLD
- [ ] Can capacity plan (DAU → QPS → servers)
- [ ] Know caching strategies and when to use each
- [ ] Understand sharding types and resharding challenges
- [ ] Know CAP theorem and can explain trade-offs
- [ ] Can draw architecture and identify bottlenecks
- [ ] Understand ACID vs eventual consistency

## Communication
- [ ] Ask clarifying questions FIRST
- [ ] Show your thinking process out loud
- [ ] Discuss trade-offs explicitly
- [ ] Ask "What happens if X fails?"
- [ ] Don't just say answers, explain WHY

---

# 🚀 Next Steps

1. **Pick 3 LLD problems** from this document and implement this week
2. **Pick 2 HLD problems** and design them (45 min each)
3. **Record yourself** doing a design and listen for weak spots
4. **Study SOLID** principles deeply (this is where most candidates fail)
5. **Read existing designs** in your repo and understand the decisions made

You've got a solid foundation already. Now go **DEEP** on fewer topics instead of wide on many. Master SOLID + 2-3 design patterns + 1 major HLD framework.

**Final tip:** Interviewers care more about your thought process than the final answer. Always explain trade-offs clearly.

Good luck! 💪
