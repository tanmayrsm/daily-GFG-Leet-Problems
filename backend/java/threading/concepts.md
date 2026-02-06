# Java Threading Concepts

## Question 1: High-Performance Trading Application

In a high-performance trading application, a single shared object is updated by one writer thread and read by many reader threads. Readers must observe a consistent and up-to-date snapshot, and latency is critical. Which pattern is most appropriate?

### Options

**A)** Use immutable snapshot objects safely published through a `volatile` reference.

**B)** Use a `ReadWriteLock` with many readers and a single writer.

**C)** Use a `synchronized` method on the shared object for both reads and writes.

**D)** Use a single shared mutable object protected by a `ReentrantLock`.

### Answer

**Correct: A**

Using immutable snapshot objects safely published through a `volatile` reference is a powerful pattern for scenarios with one writer and many readers. The writer builds a new immutable object that contains the full state and then performs a single write to a `volatile` reference to publish it. Because of the Java Memory Model, writes to `volatile` variables establish a happens-before relationship with subsequent reads, ensuring that readers always see a fully constructed, consistent snapshot. Since snapshots are immutable, there is no need for synchronization on read paths, which minimizes latency and avoids contention. This design is widely used in low-latency systems for configuration, reference data, and shared state dissemination.

### Java Example

```java
// Immutable snapshot class
public final class PriceSnapshot {
    private final double bid;
    private final double ask;
    private final long timestamp;

    public PriceSnapshot(double bid, double ask, long timestamp) {
        this.bid = bid;
        this.ask = ask;
        this.timestamp = timestamp;
    }

    public double getBid() { return bid; }
    public double getAsk() { return ask; }
    public long getTimestamp() { return timestamp; }
}

// Market data holder with volatile reference
public class MarketData {
    // Volatile reference to immutable snapshot
    private volatile PriceSnapshot currentPrice =
        new PriceSnapshot(100.0, 100.5, System.currentTimeMillis());

    // Writer thread: creates new snapshot and publishes it
    public void updatePrice(double bid, double ask) {
        currentPrice = new PriceSnapshot(bid, ask, System.currentTimeMillis());
    }

    // Reader threads: no synchronization needed, always see consistent snapshot
    public PriceSnapshot getPrice() {
        return currentPrice;
    }
}

// Usage
public class TradingApp {
    public static void main(String[] args) {
        MarketData market = new MarketData();

        // Writer thread
        Thread writer = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                market.updatePrice(100 + i * 0.1, 100.5 + i * 0.1);
                try { Thread.sleep(10); } catch (InterruptedException e) {}
            }
        });

        // Multiple reader threads (no contention, no locks needed)
        for (int t = 0; t < 5; t++) {
            new Thread(() -> {
                for (int i = 0; i < 100; i++) {
                    PriceSnapshot snap = market.getPrice();
                    System.out.println("Bid: " + snap.getBid() +
                                     ", Ask: " + snap.getAsk());
                    try { Thread.sleep(5); } catch (InterruptedException e) {}
                }
            }).start();
        }

        writer.start();
    }
}
```

**Key Benefits:**
- **No locks on read path:** Readers don't compete or block each other
- **Consistent snapshots:** Readers always see fully constructed objects
- **Low latency:** Perfect for high-frequency trading systems
- **Memory visibility:** `volatile` keyword ensures happens-before relationship

---

## Question 2: Deadlock Prevention

You are diagnosing a deadlock in a Java application. Two threads are stuck: one holds lock A and waits for lock B, while the other holds lock B and waits for lock A. Which general design practice best prevents this kind of deadlock?

### Options

**A)** Always acquire multiple locks in a consistent global order.

**B)** Catch and ignore `InterruptedException` so threads do not get stuck.

**C)** Use `Thread.yield()` to give other threads a chance to run.

**D)** Always use `synchronized` blocks instead of explicit lock classes.

### Answer

**Correct: A**

`Thread.yield()` merely hints to the scheduler that the current thread is willing to let other threads run; it does nothing to resolve circular lock dependencies. In a deadlock, threads are blocked waiting for locks that will never be released, so yielding is irrelevant: there is no progress to be made because each thread is waiting on a resource held by another in a cycle.

The correct answer is: **Always acquire multiple locks in a consistent global order.** This design approach removes the possibility of circular wait, which is essential for deadlock to occur, and therefore prevents the specific pattern observed, where two threads each hold one lock and wait for the other.
