# 🔥 Top 20 LLD Problems — What to Practice

## Tier 1: MUST DO (Asked in 80% of interviews)

### 1. **LRU Cache** ⭐⭐⭐⭐⭐
**What it tests:** HashMap + Linked List, O(1) operations, design patterns

```java
class LRUCache {
    Map<Integer, Node> cache;
    DoublyLinkedList dll;
    int capacity;

    public int get(int key) {
        if (!cache.containsKey(key)) return -1;
        Node node = cache.get(key);
        dll.moveToHead(node); // Mark as recently used
        return node.value;
    }

    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            node.value = value;
            dll.moveToHead(node);
            return;
        }

        if (cache.size() == capacity) {
            Node lru = dll.removeFromTail();
            cache.remove(lru.key);
        }

        Node newNode = new Node(key, value);
        dll.addToHead(newNode);
        cache.put(key, newNode);
    }
}
```

**Follow-ups:**
- LFU Cache (track frequency, not just recency)
- Multi-level cache (L1, L2, L3)
- Cache invalidation strategies

---

### 2. **Rate Limiter** ⭐⭐⭐⭐⭐
**What it tests:** Token bucket, sliding window, concurrency

```java
class RateLimiter {
    private final long maxRequests;
    private final long windowDurationMillis;
    private long tokensAvailable;
    private long lastRefillTime;
    private final Lock lock = new ReentrantLock();

    RateLimiter(long maxRequests, long windowDurationMillis) {
        this.maxRequests = maxRequests;
        this.windowDurationMillis = windowDurationMillis;
        this.tokensAvailable = maxRequests;
        this.lastRefillTime = System.currentTimeMillis();
    }

    public boolean allowRequest() {
        lock.lock();
        try {
            long now = System.currentTimeMillis();
            long timePassed = now - lastRefillTime;

            // Refill tokens based on time elapsed
            if (timePassed >= windowDurationMillis) {
                tokensAvailable = maxRequests;
                lastRefillTime = now;
            }

            if (tokensAvailable > 0) {
                tokensAvailable--;
                return true;
            }
            return false;
        } finally {
            lock.unlock();
        }
    }
}
```

**Variants:**
- Fixed window vs sliding window
- Per-user rate limiting
- Distributed rate limiting (across services)

---

### 3. **Thread-Safe Counter** ⭐⭐⭐⭐
**What it tests:** AtomicInteger, CAS operations, lock-free programming

```java
// Bad: Uses locks
class SyncCounter {
    private int count = 0;
    synchronized void increment() { count++; }
}

// Good: Lock-free
class AtomicCounter {
    private AtomicInteger count = new AtomicInteger(0);
    void increment() { count.incrementAndGet(); }
    int getValue() { return count.get(); }
}

// Best: For high concurrency
class CountDownLatch {
    // Use CountDownLatch for synchronization
    CountDownLatch latch = new CountDownLatch(10);
    // Thread decrements: latch.countDown()
    // Main waits: latch.await()
}
```

---

### 4. **Parking Lot System** ⭐⭐⭐⭐
**What it tests:** Multi-level hierarchy, state management, payment logic

```java
class ParkingLot {
    private List<Level> levels;
    private final int spotsPerLevel = 100;

    public boolean parkVehicle(Vehicle vehicle) {
        for (Level level : levels) {
            ParkingSpot spot = level.findAvailableSpot(vehicle.size);
            if (spot != null) {
                spot.parkVehicle(vehicle);
                return true;
            }
        }
        return false;
    }

    public double unparkVehicle(String vehicleId) {
        for (Level level : levels) {
            ParkingSpot spot = level.findSpot(vehicleId);
            if (spot != null) {
                Vehicle vehicle = spot.unparkVehicle();
                long durationHours = calculateDuration(vehicle);
                double fee = calculateFee(vehicle.size, durationHours);
                return fee;
            }
        }
        return -1;
    }
}

class Level {
    private ParkingSpot[] spots;

    ParkingSpot findAvailableSpot(VehicleSize size) {
        for (ParkingSpot spot : spots) {
            if (spot.isAvailable() && spot.canFit(size)) {
                return spot;
            }
        }
        return null;
    }
}

class ParkingSpot {
    private Vehicle parkedVehicle;
    private VehicleSize size;
    private long parkedTime;

    synchronized void parkVehicle(Vehicle v) {
        this.parkedVehicle = v;
        this.parkedTime = System.currentTimeMillis();
    }

    synchronized Vehicle unparkVehicle() {
        Vehicle v = parkedVehicle;
        parkedVehicle = null;
        return v;
    }

    boolean isAvailable() { return parkedVehicle == null; }
    boolean canFit(VehicleSize size) { return this.size.canFit(size); }
}
```

---

### 5. **Elevator System** ⭐⭐⭐⭐
**What it tests:** State machines, priority queues, task scheduling

```java
enum Direction { UP, DOWN, IDLE }
enum DoorState { OPEN, CLOSED }

class Elevator {
    private int currentFloor = 0;
    private Direction direction = IDLE;
    private DoorState doorState = CLOSED;
    private PriorityQueue<Integer> upRequests = new PriorityQueue<>();
    private PriorityQueue<Integer, Collections.reverseOrder()> downRequests;

    void requestFloor(int floor) {
        if (floor > currentFloor) upRequests.offer(floor);
        else downRequests.offer(floor);
    }

    void run() {
        while (true) {
            if (direction == UP) {
                moveUp();
            } else if (direction == DOWN) {
                moveDown();
            } else {
                decideDirection();
            }
        }
    }

    private void moveUp() {
        if (upRequests.isEmpty()) {
            direction = DOWN;
            return;
        }

        int nextFloor = upRequests.peek();
        if (currentFloor == nextFloor) {
            upRequests.poll();
            stop();
        } else {
            currentFloor++;
        }
    }

    private void stop() {
        doorState = OPEN;
        // Wait for passengers
        doorState = CLOSED;
    }
}
```

---

### 6. **Hotel Booking System** ⭐⭐⭐⭐
**What it tests:** Concurrency, date ranges, reservations

```java
class HotelBookingSystem {
    private Map<Integer, Room> rooms = new ConcurrentHashMap<>();
    private List<Booking> bookings = Collections.synchronizedList(new ArrayList<>());

    synchronized Booking bookRoom(int roomId, LocalDate start, LocalDate end, User user) {
        if (!isRoomAvailable(roomId, start, end)) {
            return null;
        }

        Booking booking = new Booking(
            UUID.randomUUID().toString(),
            roomId,
            start,
            end,
            user,
            LocalDateTime.now()
        );

        bookings.add(booking);
        return booking;
    }

    synchronized boolean isRoomAvailable(int roomId, LocalDate start, LocalDate end) {
        return bookings.stream()
            .filter(b -> b.roomId == roomId && b.status == CONFIRMED)
            .noneMatch(b -> dateRangesOverlap(start, end, b.startDate, b.endDate));
    }

    private boolean dateRangesOverlap(LocalDate s1, LocalDate e1, LocalDate s2, LocalDate e2) {
        return !e1.isBefore(s2) && !e2.isBefore(s1);
    }

    Booking cancelBooking(String bookingId) {
        Booking booking = bookings.stream()
            .filter(b -> b.id.equals(bookingId))
            .findFirst()
            .orElse(null);

        if (booking != null) {
            booking.status = CANCELLED;
            return booking;
        }
        return null;
    }
}
```

---

## Tier 2: SHOULD DO (Asked in 50% of interviews)

### 7. **Task Scheduler / Cron Job System**
```java
class TaskScheduler {
    PriorityQueue<ScheduledTask> heap;
    ExecutorService executor = Executors.newFixedThreadPool(10);

    void scheduleAtFixedRate(Task task, long initialDelay, long period) {
        executor.scheduleAtFixedRate(task, initialDelay, period, TimeUnit.MILLISECONDS);
    }

    void scheduleOnce(Task task, long delayMs) {
        executor.schedule(task, delayMs, TimeUnit.MILLISECONDS);
    }
}
```

### 8. **LFU Cache**
Track frequency, not just recency. Harder than LRU.

### 9. **Phone Directory / Trie-based System**
```java
class TrieNode {
    Map<Character, TrieNode> children = new HashMap<>();
    List<String> suggestions = new ArrayList<>();
}

class PhoneDirectory {
    TrieNode root;

    void insert(String number) {
        TrieNode node = root;
        for (char c : number.toCharArray()) {
            node = node.children.computeIfAbsent(c, k -> new TrieNode());
            node.suggestions.add(number);
        }
    }

    List<String> search(String prefix) {
        TrieNode node = root;
        for (char c : prefix.toCharArray()) {
            if (!node.children.containsKey(c)) return new ArrayList<>();
            node = node.children.get(c);
        }
        return node.suggestions;
    }
}
```

### 10. **Library Management System**
Books, members, rentals, fines

### 11. **Movie Ticket Booking System**
Similar to hotel, but with seats instead of dates

### 12. **Design a Vending Machine**
State machine, inventory management, payment

### 13. **Design a Music Player**
Playlist, shuffle, repeat, skip logic

### 14. **Publish-Subscribe System (Event Bus)**
```java
interface EventListener {
    void onEvent(Event event);
}

class EventBus {
    Map<String, List<EventListener>> subscribers = new HashMap<>();

    void subscribe(String eventType, EventListener listener) {
        subscribers.computeIfAbsent(eventType, k -> new ArrayList<>()).add(listener);
    }

    void publish(Event event) {
        subscribers.getOrDefault(event.type, new ArrayList<>())
            .forEach(listener -> listener.onEvent(event));
    }
}
```

### 15. **Design a Logger System**
Multiple log levels, async writing, log rotation

---

## Tier 3: NICE TO HAVE (Asked in 20% of interviews)

### 16. **Design a Deck of Cards**
Shuffle, deal, track dealt cards

### 17. **Tic-Tac-Toe Game**
Game logic, win detection, minimax algorithm

### 18. **Design a URL Shortener**
Hash function, collision handling, expiry

### 19. **Design a Key-Value Store**
Persistence, eviction policies, replication

### 20. **Design Snakes & Ladders**
Game simulation, player tracking

---

# 📋 Study Schedule (2 Weeks)

## **Week 1: Tier 1 Problems**
- Day 1: LRU Cache (implement from scratch, then optimize)
- Day 2: Rate Limiter (token bucket + sliding window variants)
- Day 3: Thread-safe Counter (AtomicInteger, CAS)
- Day 4: Parking Lot System (multi-level hierarchy)
- Day 5: Elevator System (state machine + priority queue)
- Day 6: Hotel Booking System (concurrency + date ranges)
- Day 7: Review all, pick weakest and redo

## **Week 2: Tier 2 + Patterns**
- Day 1: Task Scheduler (ExecutorService)
- Day 2: LFU Cache (frequency tracking)
- Day 3: Trie-based Phone Directory
- Day 4-5: Two of (Library, Movie Booking, Vending Machine)
- Day 6: Event Bus (Observer pattern)
- Day 7: Mock interview (pick any 2 problems, 45 min total)

---

# ✅ Checklist for Each Problem

When implementing, ask yourself:

- [ ] Is my design thread-safe?
- [ ] Can I handle concurrent access?
- [ ] Do I use the right data structures? (HashMap, PriorityQueue, etc.)
- [ ] Is my time complexity O(1) or O(log n) for main operations?
- [ ] Did I consider edge cases? (Empty state, max capacity, etc.)
- [ ] Can I explain the design decisions?
- [ ] Did I follow SOLID principles?
- [ ] Can I write clean, readable code without comments?

---

# 🎯 How to Practice

1. **Implement from scratch** (no looking at solutions)
2. **Time yourself**: Aim for 20-30 min for Tier 1, 30-40 min for Tier 2
3. **Review after**: Compare with reference solution
4. **Refactor**: Apply SOLID principles, optimize
5. **Implement variants**: If asked in interview, be prepared
6. **Explain out loud**: Practice your communication

---

# 🚀 Pro Tips

- **Master Tier 1 first**: These are foundation. Perfect them.
- **Recognize patterns**: Parking Lot ≈ Hotel Booking (just different domain)
- **Use existing patterns**: Factory, Observer, Strategy will appear in multiple problems
- **Think concurrency first**: Assume multi-threaded environment
- **Ask clarifying questions**: "How many concurrent users?" "What's the scale?"

Good luck! 💪
