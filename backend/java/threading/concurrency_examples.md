# Java Concurrency Examples – Practical Patterns

## 1. AtomicInteger – thread-safe counter

**Goal:** Safely count from multiple threads without `synchronized`.

```java
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerExample {
    public static void main(String[] args) throws InterruptedException {
        AtomicInteger counter = new AtomicInteger(0);

        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                counter.incrementAndGet();   // atomic ++
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        Thread t3 = new Thread(task);

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        System.out.println("Counter = " + counter.get()); // 3000
    }
}
```

**Use when:** You need a shared int/long that many threads update (hits, retries, etc.) without locks.

**Interview mention:**
> "For tracking concurrent requests or retry counts across threads, I'd use AtomicInteger instead of synchronized blocks—it's lock-free and faster for simple counters."

---

## 2. BlockingQueue + LinkedBlockingQueue – producer/consumer

**Goal:** One thread produces work, others consume, with blocking behavior.

```java
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class BlockingQueueExample {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(3); // capacity = 3

        Thread producer = new Thread(() -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    queue.put(i); // blocks if queue is full
                    System.out.println("Produced " + i);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread consumer = new Thread(() -> {
            try {
                while (true) {
                    Integer value = queue.take(); // blocks if empty
                    System.out.println("Consumed " + value);
                    Thread.sleep(500);            // slow consumer
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        producer.start();
        consumer.start();

        producer.join();
        Thread.sleep(3000);   // wait a bit then end program
    }
}
```

**Use when:** You want to **decouple** producer and consumer speed with built-in blocking and thread safety.

**Interview mention:**
> "For asynchronous task processing—like booking requests going to worker threads—I'd use a BlockingQueue. It handles backpressure naturally: if consumers are slow, producers block instead of overwhelming memory."

---

## 3. Semaphore – limit how many can run at once

**Goal:** Only N threads can enter a critical section concurrently (basic rate limiting).

```java
import java.util.concurrent.Semaphore;

public class SemaphoreExample {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(2); // max 2 concurrent

        Runnable task = () -> {
            try {
                semaphore.acquire();        // wait for a permit
                System.out.println(Thread.currentThread().getName()
                        + " entered critical section");
                Thread.sleep(1000);        // simulate work
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                System.out.println(Thread.currentThread().getName()
                        + " leaving");
                semaphore.release();        // give permit back
            }
        };

        for (int i = 0; i < 5; i++) {
            new Thread(task, "T" + i).start();
        }
    }
}
```

**Use when:** You need to **cap concurrency**: DB connections, external API calls, etc.

**Interview mention:**
> "To prevent overwhelming a third-party API or database, I'd use a Semaphore to limit concurrent calls—say max 10 parallel requests to a hotel availability API. Threads wait for a permit before calling, ensuring we never exceed the limit."

---

## Real-World Agoda-Style Use Cases

### AtomicInteger
- **Request counter**: Track API requests per second across multiple handler threads
- **Retry counter**: Count failed payment retries without locking
- **Active sessions**: Increment/decrement active user count

### BlockingQueue
- **Async booking processing**: API threads put booking requests in queue, worker threads process them
- **Email/SMS notifications**: Producer adds notification tasks, consumer pool sends them
- **Event logging**: Fast path puts log events in queue, background thread writes to disk/DB

### Semaphore
- **Database connection pool**: Limit concurrent DB connections to prevent overload
- **External API rate limiting**: Cap parallel calls to hotel provider APIs
- **Resource-intensive operations**: Limit concurrent PDF generation or image processing threads
