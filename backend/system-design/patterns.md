# System Design Patterns

## Token Bucket (Rate Limiting)

- The token bucket algorithm controls how many requests are allowed over time, while still allowing short bursts.
- You track:
  - `capacity` (max tokens),
  - `tokens` (current tokens),
  - `lastRefillTime` (last time you updated tokens).
- On each request:
  - Compute how much time has passed since `lastRefillTime`, add `rate * elapsed` tokens, cap at `capacity`, update `lastRefillTime`.
  - If `tokens > 0`, decrement and allow the request; otherwise, reject or delay it.

### Implementation Example

```pseudo
class TokenBucket:
  capacity: int       // max tokens
  tokens: float       // current tokens
  rate: float         // tokens per second
  lastRefillTime: timestamp

  function allowRequest():
    now = currentTime()
    elapsed = now - lastRefillTime

    // Refill tokens based on elapsed time
    tokens = min(capacity, tokens + rate * elapsed)
    lastRefillTime = now

    // Check if request can be allowed
    if tokens >= 1:
      tokens -= 1
      return true
    else:
      return false
```

### Use Cases

- **API rate limiting**: Limit requests per user/IP to prevent abuse
- **Traffic shaping**: Allow bursts but control average rate
- **Resource protection**: Prevent overwhelming downstream services

### Agoda Context

- **Search API**: Allow 100 searches/minute per user, with burst capacity of 20
- **Booking API**: Strict rate limit to prevent bot attacks on flash sales
- **Third-party integrations**: Rate limit calls to external hotel APIs
