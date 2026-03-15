import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

public class RateLimiterExample {

    static class RequestContext {
        final String userId;
        final String apiId;
        final String method;
        final String path;

        RequestContext(String userId, String apiId, String method, String path) {
            this.userId = userId;
            this.apiId = apiId;
            this.method = method;
            this.path = path;
        }
    }

    static class RateLimitResult {
        final boolean allowed;
        final long retryAfterMillis;

        RateLimitResult(boolean allowed, long retryAfterMillis) {
            this.allowed = allowed;
            this.retryAfterMillis = retryAfterMillis;
        }

        static RateLimitResult allow() {
            return new RateLimitResult(true, 0);
        }

        static RateLimitResult reject(long retryAfterMillis) {
            return new RateLimitResult(false, Math.max(1, retryAfterMillis));
        }
    }

    enum RateLimitPolicyType {
        FIXED_WINDOW,
        SLIDING_WINDOW,
        TOKEN_BUCKET
    }

    static class RateLimitPolicy {
        final int limit;
        final long windowMillis;
        final RateLimitPolicyType type;

        RateLimitPolicy(int limit, long windowMillis, RateLimitPolicyType type) {
            this.limit = limit;
            this.windowMillis = windowMillis;
            this.type = type;
        }
    }

    interface RateLimitPolicyStore {
        RateLimitPolicy getPolicyFor(String key, String apiId);
    }

    static class InMemoryRateLimitPolicyStore implements RateLimitPolicyStore {
        private final Map<String, RateLimitPolicy> perApi = new HashMap<>();
        private final RateLimitPolicy defaultPolicy;

        InMemoryRateLimitPolicyStore(RateLimitPolicy defaultPolicy) {
            this.defaultPolicy = defaultPolicy;
        }

        void putForApi(String apiId, RateLimitPolicy policy) {
            perApi.put(apiId, policy);
        }

        @Override
        public RateLimitPolicy getPolicyFor(String key, String apiId) {
            return perApi.getOrDefault(apiId, defaultPolicy);
        }
    }

    interface RateLimitStrategy {
        RateLimitResult evaluate(String key, RateLimitPolicy policy, long nowMillis);
    }

    static class StateStore<T> {
        private final ConcurrentHashMap<String, T> stateMap = new ConcurrentHashMap<>();

        T getOrCreateState(String key, Supplier<T> initialStateSupplier) {
            return stateMap.computeIfAbsent(key, k -> initialStateSupplier.get());
        }
    }

    static class FixedWindowState {
        long windowStartMillis;
        int count;
    }

    static class FixedWindowStrategy implements RateLimitStrategy {
        private final StateStore<FixedWindowState> stateStore = new StateStore<>();

        @Override
        public RateLimitResult evaluate(String key, RateLimitPolicy policy, long nowMillis) {
            FixedWindowState state = stateStore.getOrCreateState(key, FixedWindowState::new);
            synchronized (state) {
                if (state.windowStartMillis == 0) {
                    state.windowStartMillis = nowMillis;
                }
                long elapsed = nowMillis - state.windowStartMillis;
                if (elapsed >= policy.windowMillis) {
                    state.windowStartMillis = nowMillis;
                    state.count = 0;
                }
                if (state.count < policy.limit) {
                    state.count++;
                    return RateLimitResult.allow();
                }
                long retryAfter = policy.windowMillis - (nowMillis - state.windowStartMillis);
                return RateLimitResult.reject(retryAfter);
            }
        }
    }

    static class SlidingWindowState {
        final Deque<Long> timestamps = new ArrayDeque<>();
    }

    static class SlidingWindowStrategy implements RateLimitStrategy {
        private final StateStore<SlidingWindowState> stateStore = new StateStore<>();

        @Override
        public RateLimitResult evaluate(String key, RateLimitPolicy policy, long nowMillis) {
            SlidingWindowState state = stateStore.getOrCreateState(key, SlidingWindowState::new);
            synchronized (state) {
                while (!state.timestamps.isEmpty()
                    && nowMillis - state.timestamps.peekFirst() >= policy.windowMillis) {
                    state.timestamps.pollFirst();
                }
                if (state.timestamps.size() < policy.limit) {
                    state.timestamps.offerLast(nowMillis);
                    return RateLimitResult.allow();
                }
                long oldest = state.timestamps.peekFirst();
                long retryAfter = policy.windowMillis - (nowMillis - oldest);
                return RateLimitResult.reject(retryAfter);
            }
        }
    }

    static class TokenBucketState {
        double tokens;
        long lastRefillMillis;
    }

    static class TokenBucketStrategy implements RateLimitStrategy {
        private final StateStore<TokenBucketState> stateStore = new StateStore<>();

        @Override
        public RateLimitResult evaluate(String key, RateLimitPolicy policy, long nowMillis) {
            TokenBucketState state = stateStore.getOrCreateState(key, TokenBucketState::new);
            synchronized (state) {
                if (state.lastRefillMillis == 0) {
                    state.lastRefillMillis = nowMillis;
                    state.tokens = policy.limit;
                }

                double refillRatePerMilli = (double) policy.limit / policy.windowMillis;
                long elapsed = nowMillis - state.lastRefillMillis;
                double refilled = elapsed * refillRatePerMilli;
                state.tokens = Math.min(policy.limit, state.tokens + refilled);
                state.lastRefillMillis = nowMillis;

                if (state.tokens >= 1.0) {
                    state.tokens -= 1.0;
                    return RateLimitResult.allow();
                }

                long retryAfter = (long) Math.ceil((1.0 - state.tokens) / refillRatePerMilli);
                return RateLimitResult.reject(retryAfter);
            }
        }
    }

    static class RateLimitStrategyRegistry {
        private final Map<RateLimitPolicyType, RateLimitStrategy> strategies;

        RateLimitStrategyRegistry(Map<RateLimitPolicyType, RateLimitStrategy> strategies) {
            this.strategies = strategies;
        }

        RateLimitStrategy getStrategy(RateLimitPolicyType type) {
            RateLimitStrategy strategy = strategies.get(type);
            if (strategy == null) {
                throw new IllegalArgumentException("No strategy for type: " + type);
            }
            return strategy;
        }
    }

    interface RateLimiter {
        RateLimitResult check(RequestContext ctx);
    }

    static class ConfigurableRateLimiter implements RateLimiter {
        private final RateLimitPolicyStore policyStore;
        private final RateLimitStrategyRegistry strategyRegistry;

        ConfigurableRateLimiter(
            RateLimitPolicyStore policyStore,
            RateLimitStrategyRegistry strategyRegistry
        ) {
            this.policyStore = policyStore;
            this.strategyRegistry = strategyRegistry;
        }

        @Override
        public RateLimitResult check(RequestContext ctx) {
            String key = ctx.userId + ":" + ctx.apiId;
            RateLimitPolicy policy = policyStore.getPolicyFor(key, ctx.apiId);
            RateLimitStrategy strategy = strategyRegistry.getStrategy(policy.type);
            return strategy.evaluate(key, policy, System.currentTimeMillis());
        }
    }

    abstract static class RateLimiterDecorator implements RateLimiter {
        protected final RateLimiter inner;

        protected RateLimiterDecorator(RateLimiter inner) {
            this.inner = inner;
        }
    }

    static class AuditLoggingRateLimiter extends RateLimiterDecorator {
        AuditLoggingRateLimiter(RateLimiter inner) {
            super(inner);
        }

        @Override
        public RateLimitResult check(RequestContext ctx) {
            RateLimitResult result = inner.check(ctx);
            System.out.println(
                "[AUDIT] user=" + ctx.userId + " api=" + ctx.apiId + " allowed=" + result.allowed
            );
            return result;
        }
    }

    static class MetricsRateLimiter extends RateLimiterDecorator {
        MetricsRateLimiter(RateLimiter inner) {
            super(inner);
        }

        @Override
        public RateLimitResult check(RequestContext ctx) {
            long start = System.nanoTime();
            RateLimitResult result = inner.check(ctx);
            long end = System.nanoTime();
            System.out.println("[METRIC] checkLatencyNanos=" + (end - start));
            return result;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        InMemoryRateLimitPolicyStore policyStore = new InMemoryRateLimitPolicyStore(
            new RateLimitPolicy(5, 1000, RateLimitPolicyType.FIXED_WINDOW)
        );
        policyStore.putForApi("search", new RateLimitPolicy(3, 1000, RateLimitPolicyType.SLIDING_WINDOW));
        policyStore.putForApi("payments", new RateLimitPolicy(2, 1000, RateLimitPolicyType.TOKEN_BUCKET));

        Map<RateLimitPolicyType, RateLimitStrategy> map = new HashMap<>();
        map.put(RateLimitPolicyType.FIXED_WINDOW, new FixedWindowStrategy());
        map.put(RateLimitPolicyType.SLIDING_WINDOW, new SlidingWindowStrategy());
        map.put(RateLimitPolicyType.TOKEN_BUCKET, new TokenBucketStrategy());
        RateLimitStrategyRegistry registry = new RateLimitStrategyRegistry(map);

        RateLimiter limiter = new ConfigurableRateLimiter(policyStore, registry);
        limiter = new MetricsRateLimiter(new AuditLoggingRateLimiter(limiter));

        RequestContext ctx = new RequestContext("u1", "search", "GET", "/v1/search");
        for (int i = 1; i <= 5; i++) {
            RateLimitResult r = limiter.check(ctx);
            System.out.println(
                "request#" + i + " allowed=" + r.allowed + " retryAfterMillis=" + r.retryAfterMillis
            );
            Thread.sleep(150);
        }
    }
}

