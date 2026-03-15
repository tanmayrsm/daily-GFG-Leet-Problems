# Configurable Rate Limiter (Java LLD)

This is the concrete LLD breakdown for a configurable rate limiter using:
- Policy
- Strategy
- Facade
- Registry/Factory style wiring
- Decorator
- Encapsulated state + concurrency

## 1. Domain

```java
RequestContext { userId, apiId, method, path }
RateLimitResult { allowed, retryAfterMillis }
```

## 2. Policy

```java
enum RateLimitPolicyType { FIXED_WINDOW, SLIDING_WINDOW, TOKEN_BUCKET }

class RateLimitPolicy {
    int limit;
    long windowMillis;
    RateLimitPolicyType type;
}

interface RateLimitPolicyStore {
    RateLimitPolicy getPolicyFor(String key, String apiId);
}
```

Policy decides which algorithm should run and with what limits.

## 3. Strategy

```java
interface RateLimitStrategy {
    RateLimitResult evaluate(String key, RateLimitPolicy policy, long nowMillis);
}
```

Concrete strategies:
- `FixedWindowStrategy`
- `SlidingWindowStrategy`
- `TokenBucketStrategy`

Registry:

```java
class RateLimitStrategyRegistry {
    Map<RateLimitPolicyType, RateLimitStrategy> strategies;
    RateLimitStrategy getStrategy(RateLimitPolicyType type) { ... }
}
```

## 4. State and Concurrency

Each strategy owns its own state store with `ConcurrentHashMap` and per-key synchronization.

Examples:
- `FixedWindowState { windowStartMillis, count }`
- `SlidingWindowState { Deque<Long> timestamps }`
- `TokenBucketState { tokens, lastRefillMillis }`

Keep locking inside strategy `evaluate(...)`.

## 5. Facade

```java
interface RateLimiter {
    RateLimitResult check(RequestContext ctx);
}

class ConfigurableRateLimiter implements RateLimiter {
    // key -> policy -> strategy -> evaluate -> result
}
```

Flow:
1. Build key (`userId:apiId`)
2. Fetch policy
3. Resolve strategy from registry
4. Evaluate and return result

## 6. Decorators

Decorator wrappers add non-functional concerns:
- `AuditLoggingRateLimiter`
- `MetricsRateLimiter`

They implement `RateLimiter`, call inner limiter, then log/record metrics.

## 7. Actual Java Example

See:
- [RateLimiterExample.java](/Users/tamishra/Documents/dlyPrb/backend/system-design/LLD/RateLimiterExample.java)

