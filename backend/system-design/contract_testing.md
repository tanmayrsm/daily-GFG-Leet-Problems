# Contract Testing with Pact

## TL;DR

- Use Pact only between services you and your org control.
- You publish contracts from your CI to a Pact Broker.
- Provider's CI pulls from the broker and runs verification.
- Broker webhooks/CI status tell both sides when a change breaks the contract.

---

## What is Pact?

Pact is a consumer-driven contract testing tool that helps ensure microservices remain compatible without needing full end-to-end tests.

Key benefits:
- Earlier detection of breaking changes
- No need for full integration environments
- Clear expectations between teams
- Safe deployments with compatibility verification

---

## The Pact Workflow

### Consumer Side (Your Service)

1. **Write Pact tests around your client code**
   - Your test says: "When I call `GET /users/123`, I expect status 200 and a body that has at least `id` and `name`."
   - Pact spins up a mock server, you call it like it's the real API, and it checks that your client can handle the response structure.

2. **Pact generates a pact file**
   - That file is basically: "consumer X expects provider Y to behave like this for these interactions."
   - The pact file is a JSON document describing all expected interactions.

3. **Your CI publishes that pact file to the Pact Broker**
   - Broker is just a shared place (service + UI/API) where pacts and verification results live.
   - Now anyone (including the provider team) can see: consumer version, provider name, what endpoints and responses are expected.

---

### Provider Side (Their Service)

4. **Broker can trigger their CI when a new/changed contract appears**
   - Using webhooks: "New contract requiring verification for provider X" → run provider verification job.

5. **Their CI spins up the real provider and runs Pact provider verification**
   - It pulls the pact(s) from the broker for their provider name.
   - For each recorded interaction (your expectations), the verifier calls their real API and checks: "Does the real response match what the consumer expects?"
   - If it doesn't match, the verification fails and their build goes red; the failure is also stored back in the broker.

6. **Broker now has a matrix of who is compatible with whom**
   - For each `(consumer version, provider version)` pair, it knows pass/fail from past verifications.
   - Your CI can call `can-i-deploy` before release: "Is consumer v123 compatible with all providers I depend on?" If not, your deployment is blocked.

---

## High-Level Flow

```text
[Your Service Code]
       |
       v
[Consumer Pact Tests]
       |
       v
[Generate Pact JSON]
       |
       v
[Publish to Pact Broker]
       |
       |  (Webhook: "contract requiring verification")
       v
[Provider CI Pipeline]
       |
       v
[Start Provider API in CI]
       |
       v
[Provider Pact Verification]
       |
   +---+-------------------------+
   |                             |
   v                             v
[Verification Passed]       [Verification Failed]
   |                             |
   |                             +--> Notify teams / fail build
   |                                   (Slack/PR/GitHub status)
   v
[Broker stores status]
   |
   v
["Can I Deploy?" checks before release]
```

This is the core Pact loop: you define expectations, publish to a shared broker, providers verify in their CI, and failures become build breaks/notifications instead of prod incidents.

---

## How This Helps You

- You don't need full end-to-end environments just to know if an API change will break you.
- You get earlier, clearer signals:
  - As soon as your expectations change, a verification job runs.
  - As soon as they make a breaking change, their verification fails, and the broker shows "this provider version is not compatible with your consumer version."

---

## Handling Downstream Dependencies

### Scenario: Provider's Downstream is Down

If their service calls another downstream that is down, Pact itself doesn't see that extra hop; it only checks the contract between **you** and **them**.

**In Pact verification:**

- Their Pact verification test hits **their** API (provider) with the request from your contract.
- Inside that call, their code tries to call its own downstream C, which is down.

**Result options:**

1. **They return some error (5xx/timeout/whatever) to the verifier**
   - Verification fails because it doesn't match the contract (e.g., you expected 200 + body).

2. **They stub/mock their own downstream C in provider tests**
   - Verification still passes, because Pact only cares that their public API response matches your pact, not whether C is healthy.

**Key Point:** Pact will only flag it if the failure of their downstream changes **their** response in a way that breaks the contract you agreed on.

---

## Best Practices

### For Consumers

1. **Test realistic scenarios**
   - Include edge cases (empty lists, null values, pagination)
   - Test error responses you handle (404, 400, 500)

2. **Use provider states**
   - "Given user 123 exists" before calling `GET /users/123`
   - Provider can set up test data based on these states

3. **Keep contracts focused**
   - Only assert on fields you actually use
   - Don't over-specify (e.g., don't check exact timestamps)

### For Providers

1. **Verify against all consumer versions you support**
   - Use broker to verify against all active consumer versions
   - Don't break old consumers until they upgrade

2. **Use provider states to set up test data**
   - Clean setup/teardown for each interaction
   - Use in-memory DB or test fixtures

3. **Run verification on every deployment**
   - Verify pacts in CI before merging
   - Block deployment if verification fails

### For Both Teams

1. **Communicate breaking changes early**
   - Use broker to see which consumers will be affected
   - Coordinate version upgrades

2. **Use semantic versioning**
   - Major version bump = breaking change
   - Broker can enforce compatibility rules

3. **Monitor the Pact Broker**
   - Set up alerts for verification failures
   - Review compatibility matrix before releases

---

## Example: Agoda-Style Usage

### Consumer: Search Service → Provider: Hotel Service

**Consumer Pact Test:**

```javascript
describe('Hotel Service Contract', () => {
  it('returns hotel details by ID', async () => {
    await provider.addInteraction({
      state: 'hotel 123 exists',
      uponReceiving: 'a request for hotel details',
      withRequest: {
        method: 'GET',
        path: '/hotels/123',
        headers: { 'Accept': 'application/json' }
      },
      willRespondWith: {
        status: 200,
        headers: { 'Content-Type': 'application/json' },
        body: {
          id: 123,
          name: like('Marriott Chennai'),
          rating: decimal(4.5),
          city: like('Chennai')
        }
      }
    });

    const response = await hotelClient.getHotel(123);
    expect(response.id).toBe(123);
    expect(response.name).toBeDefined();
  });
});
```

**Provider Verification:**

```javascript
const verifier = new Verifier({
  provider: 'Hotel Service',
  providerBaseUrl: 'http://localhost:8080',
  pactBrokerUrl: 'https://pact-broker.agoda.com',
  publishVerificationResult: true,
  providerVersion: process.env.GIT_COMMIT,
  stateHandlers: {
    'hotel 123 exists': async () => {
      // Set up test data
      await testDb.hotels.insert({ id: 123, name: 'Marriott Chennai', ... });
    }
  }
});

await verifier.verifyProvider();
```

---

## When NOT to Use Pact

- **Third-party APIs you don't control** (Stripe, Google Maps, etc.)
  - Use API mocks or VCR-style recording instead
  - Provider won't run your pacts

- **Simple internal calls that rarely change**
  - Overhead may not be worth it
  - Integration tests might be simpler

- **UI/frontend testing**
  - Pact is for API contracts, not DOM/rendering
  - Use component tests or E2E for UI
