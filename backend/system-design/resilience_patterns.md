# Resilience Patterns for External APIs

## Handling Partner APIs: Down vs. Stale Data

### Partner API Down / Very Slow

#### 1. Timeouts + Limited Retries

- Use **short timeouts** (e.g., 300–800 ms) and a few retries with exponential backoff, only for non-UI-critical flows.

```java
public HotelAvailability getAvailability(String hotelId) {
    int maxRetries = 3;
    int baseDelay = 100; // ms

    for (int i = 0; i < maxRetries; i++) {
        try {
            return partnerClient.getAvailability(hotelId)
                .timeout(Duration.ofMillis(500));
        } catch (TimeoutException | ServiceException e) {
            if (i == maxRetries - 1) {
                throw e; // Final attempt failed
            }
            // Exponential backoff with jitter
            Thread.sleep(baseDelay * (1 << i) + random.nextInt(100));
        }
    }
}
```

#### 2. Circuit Breaker

- If error/timeout rate is high, **open the circuit** and temporarily stop calling that partner.
- While open, either skip their inventory or use slightly stale cached data within a safe freshness window.

```java
@CircuitBreaker(name = "partner-api", fallbackMethod = "getAvailabilityFallback")
public HotelAvailability getAvailability(String hotelId) {
    return partnerClient.getAvailability(hotelId);
}

public HotelAvailability getAvailabilityFallback(String hotelId, Exception e) {
    // Use cached data if available and fresh enough
    CachedAvailability cached = cache.get("availability:" + hotelId);
    if (cached != null && cached.age() < Duration.ofMinutes(15)) {
        log.warn("Using cached availability for {} due to partner failure", hotelId);
        return cached.toAvailability();
    }
    // Otherwise, skip this partner's inventory
    return HotelAvailability.unavailable();
}
```

**Circuit Breaker States:**

```
CLOSED (Normal)
   |
   | (Failure rate > threshold)
   v
OPEN (Block all calls)
   |
   | (After timeout period)
   v
HALF_OPEN (Allow test calls)
   |
   +---> Success? --> CLOSED
   |
   +---> Failure? --> OPEN
```

**Configuration Example (Resilience4j):**

```yaml
resilience4j:
  circuitbreaker:
    instances:
      partner-api:
        failureRateThreshold: 50        # Open if 50% fail
        slowCallRateThreshold: 50       # Open if 50% slow
        slowCallDurationThreshold: 2s   # Define "slow"
        waitDurationInOpenState: 30s    # How long to stay open
        permittedNumberOfCallsInHalfOpenState: 5
        slidingWindowSize: 100          # Last 100 calls
```

#### 3. Graceful Degradation in UI

- Still show results from other partners/sources.
- Optionally show a small notice like "Some partners temporarily unavailable; results may be limited."

```javascript
// Frontend handling
function displayHotels(results, partnerStatuses) {
    // Show available results
    renderHotels(results.hotels);

    // Check if any partners failed
    const failedPartners = partnerStatuses.filter(p => p.status === 'unavailable');
    if (failedPartners.length > 0) {
        showWarning(
            `${failedPartners.length} partner(s) temporarily unavailable. ` +
            `Showing results from available sources.`
        );
    }
}
```

#### 4. Async Queues for Non-Realtime Work

- For reconciliation, rate updates, etc., push tasks to a queue and retry later when the partner recovers (with DLQ for permanent issues).

```java
public void syncPartnerRates(String partnerId) {
    try {
        List<Rate> rates = partnerClient.getRates(partnerId);
        updateRatesInDB(rates);
    } catch (PartnerUnavailableException e) {
        // Push to queue for retry
        SyncTask task = new SyncTask(partnerId, LocalDateTime.now());
        rateSyncQueue.send(task);
        log.warn("Partner {} unavailable, queued for retry", partnerId);
    } catch (Exception e) {
        // Permanent error -> DLQ
        deadLetterQueue.send(new FailedTask(partnerId, e.getMessage()));
        log.error("Permanent failure syncing partner {}", partnerId, e);
    }
}
```

**Queue Processing with Backoff:**

```java
@KafkaListener(topics = "rate-sync-retry")
public void processRetry(SyncTask task) {
    if (task.getRetryCount() > MAX_RETRIES) {
        deadLetterQueue.send(task);
        return;
    }

    try {
        syncPartnerRates(task.getPartnerId());
    } catch (Exception e) {
        // Exponential backoff
        long delayMs = Math.min(
            1000L * (1L << task.getRetryCount()),
            3600000L  // Max 1 hour
        );
        task.incrementRetry();
        scheduler.schedule(() -> rateSyncQueue.send(task), delayMs);
    }
}
```

---

### Partner Data Stale

#### 1. Track Freshness Per Record

- Store `last_updated_at` or version on partner availability/price rows.
- If older than the SLA (e.g., >10–15 minutes), treat as low confidence and maybe hide or down-rank.

```sql
CREATE TABLE partner_availability (
    partner_id INT,
    hotel_id INT,
    room_type VARCHAR(50),
    available_rooms INT,
    price DECIMAL(10,2),
    last_updated_at TIMESTAMP NOT NULL,
    data_version INT,
    PRIMARY KEY (partner_id, hotel_id, room_type)
);

CREATE INDEX idx_freshness ON partner_availability(last_updated_at);
```

```sql
-- Query with freshness check
SELECT *
FROM partner_availability
WHERE hotel_id = :hotel_id
  AND last_updated_at > NOW() - INTERVAL '15 minutes'
  AND available_rooms > 0
ORDER BY price ASC;
```

**Freshness-based ranking:**

```java
public List<HotelOption> rankResults(List<HotelOption> options) {
    return options.stream()
        .map(option -> {
            Duration age = Duration.between(option.getLastUpdated(), Instant.now());
            double freshnessPenalty = age.toMinutes() > 10 ? 0.8 : 1.0;
            option.setRankScore(option.getBaseScore() * freshnessPenalty);
            return option;
        })
        .sorted(Comparator.comparing(HotelOption::getRankScore).reversed())
        .collect(Collectors.toList());
}
```

#### 2. Cache + TTL + Safety Buffers

- Cache partner data with TTL.
- To reduce overbooking risk, keep a safety margin (e.g., do not sell the last 1 room from that partner if data is close to expiring).

```java
public int getBookableRooms(PartnerAvailability availability) {
    Duration age = Duration.between(availability.getLastUpdated(), Instant.now());
    int availableRooms = availability.getAvailableRooms();

    // Safety buffer based on data age
    if (age.toMinutes() > 10) {
        // Data is getting stale, apply safety buffer
        return Math.max(0, availableRooms - 2);
    } else if (age.toMinutes() > 5) {
        // Moderate staleness
        return Math.max(0, availableRooms - 1);
    } else {
        // Fresh data
        return availableRooms;
    }
}
```

**Redis cache with TTL:**

```java
public HotelAvailability getAvailabilityWithCache(String hotelId) {
    String cacheKey = "availability:" + hotelId;

    // Try cache first
    HotelAvailability cached = redis.get(cacheKey, HotelAvailability.class);
    if (cached != null) {
        return cached;
    }

    // Cache miss, fetch from partner
    HotelAvailability fresh = partnerClient.getAvailability(hotelId);

    // Cache with 5 minute TTL
    redis.setex(cacheKey, 300, fresh);

    return fresh;
}
```

#### 3. Just-in-Time Revalidation on Booking

- At "Confirm booking", call the partner to re-check availability and price.
- If they changed, either fail with a clear message or offer alternatives (other rooms/suppliers).

```java
public BookingConfirmation confirmBooking(BookingRequest request) {
    // Step 1: Re-check availability and price in real-time
    PartnerAvailability current = partnerClient.getAvailabilityRealtime(
        request.getHotelId(),
        request.getRoomType()
    );

    // Step 2: Compare with what user saw
    if (current.getPrice() != request.getExpectedPrice()) {
        throw new PriceChangedException(
            "Price changed from " + request.getExpectedPrice() +
            " to " + current.getPrice(),
            current.getPrice()
        );
    }

    if (current.getAvailableRooms() < request.getRoomCount()) {
        throw new InsufficientAvailabilityException(
            "Only " + current.getAvailableRooms() + " rooms available"
        );
    }

    // Step 3: Proceed with booking
    return partnerClient.createBooking(request);
}
```

**Frontend handling:**

```javascript
async function confirmBooking(booking) {
    try {
        const result = await api.confirmBooking(booking);
        showSuccess("Booking confirmed!");
        return result;
    } catch (error) {
        if (error.type === 'PRICE_CHANGED') {
            // Offer user to proceed with new price
            const proceed = await confirm(
                `Price changed to ${error.newPrice}. Continue?`
            );
            if (proceed) {
                booking.expectedPrice = error.newPrice;
                return confirmBooking(booking); // Retry
            }
        } else if (error.type === 'INSUFFICIENT_AVAILABILITY') {
            showError("Sorry, this room is no longer available.");
            // Suggest alternatives
            showAlternatives(booking.hotelId);
        }
    }
}
```

#### 4. Periodic Reconciliation

- Background jobs compare your bookings/availability vs partner/hotel records.
- Flag mismatches into a reconciliation table/dashboard for auto-fix or manual review.

```java
@Scheduled(cron = "0 0 * * * *") // Every hour
public void reconcilePartnerData() {
    List<Partner> partners = partnerRepository.findAll();

    for (Partner partner : partners) {
        try {
            reconcilePartner(partner);
        } catch (Exception e) {
            log.error("Reconciliation failed for partner {}", partner.getId(), e);
            alerting.sendAlert("Reconciliation failure: " + partner.getName());
        }
    }
}

private void reconcilePartner(Partner partner) {
    // Fetch current state from partner
    List<Booking> partnerBookings = partnerClient.getBookings(
        partner.getId(),
        LocalDate.now().minusDays(7),
        LocalDate.now()
    );

    // Compare with our records
    List<Booking> ourBookings = bookingRepository.findByPartner(
        partner.getId(),
        LocalDate.now().minusDays(7),
        LocalDate.now()
    );

    // Find mismatches
    List<Mismatch> mismatches = findMismatches(partnerBookings, ourBookings);

    if (!mismatches.isEmpty()) {
        // Store for review
        mismatches.forEach(mismatch -> {
            reconciliationRepository.save(new ReconciliationIssue(
                partner.getId(),
                mismatch.getBookingId(),
                mismatch.getType(),
                mismatch.getDescription(),
                ReconciliationStatus.PENDING
            ));
        });

        // Auto-fix if possible
        mismatches.stream()
            .filter(Mismatch::isAutoFixable)
            .forEach(this::autoFix);
    }
}
```

**Reconciliation Table:**

```sql
CREATE TABLE reconciliation_issues (
    id BIGSERIAL PRIMARY KEY,
    partner_id INT NOT NULL,
    booking_id VARCHAR(100),
    issue_type VARCHAR(50) NOT NULL,  -- MISSING_BOOKING, PRICE_MISMATCH, etc.
    description TEXT,
    our_value VARCHAR(200),
    partner_value VARCHAR(200),
    status VARCHAR(20) DEFAULT 'PENDING',  -- PENDING, AUTO_FIXED, MANUAL_REVIEW, RESOLVED
    created_at TIMESTAMP DEFAULT NOW(),
    resolved_at TIMESTAMP,
    resolution_notes TEXT
);
```

---

## Monitoring and Alerting

### Key Metrics to Track

```yaml
# Partner API Health
partner_api_success_rate:
  threshold: 95%
  alert_if_below: true
  window: 5m

partner_api_latency_p95:
  threshold: 1000ms
  alert_if_above: true

partner_api_circuit_breaker_state:
  alert_if: state == "OPEN"

# Data Freshness
partner_data_age_p95:
  threshold: 15m
  alert_if_above: true

stale_records_count:
  threshold: 1000
  alert_if_above: true

# Reconciliation
reconciliation_issues_count:
  threshold: 100
  alert_if_above: true
  priority: medium

unresolved_mismatches_age:
  threshold: 24h
  alert_if_above: true
  priority: high
```

### Dashboard Example

```
┌─────────────────────────────────────────┐
│ Partner API Health (Last 24h)          │
├─────────────────────────────────────────┤
│ Partner A:  ✓ 99.5% up, 450ms p95      │
│ Partner B:  ⚠ 89.2% up, 1200ms p95     │
│ Partner C:  ✗ Circuit OPEN (30m ago)   │
└─────────────────────────────────────────┘

┌─────────────────────────────────────────┐
│ Data Freshness                          │
├─────────────────────────────────────────┤
│ Fresh (<5m):     85,234 records         │
│ Acceptable (<15m): 12,456 records       │
│ Stale (>15m):      1,234 records ⚠      │
└─────────────────────────────────────────┘

┌─────────────────────────────────────────┐
│ Reconciliation Queue                    │
├─────────────────────────────────────────┤
│ Pending:         23 issues              │
│ Auto-fixed:      145 (last 24h)         │
│ Manual review:   5 issues               │
└─────────────────────────────────────────┘
```

---

## Interview Summary

> "For partner API failures, I'd use a **circuit breaker pattern** with timeouts and retries. If a partner is consistently down, the circuit opens and we either skip their inventory or use cached data within a safe freshness window. For the UI, we'd show results from available partners and display a notice about limited results.
>
> For **stale data**, I'd track `last_updated_at` on all partner records and apply safety buffers—for example, not selling the last room if data is older than 10 minutes. At booking confirmation, we'd **revalidate in real-time** with the partner to catch any changes before charging the customer.
>
> We'd also run **hourly reconciliation jobs** that compare our state with partner APIs, flagging mismatches for auto-fix or manual review. This catches issues like missed webhooks or data sync failures.
>
> The key is **graceful degradation**—the system should continue serving users even when partners are down, while minimizing overbooking risk through safety margins and just-in-time validation."
