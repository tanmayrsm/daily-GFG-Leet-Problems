# JWT & Session Cookies – Notes

## 1. JWT basics

A JWT has three base64url-encoded parts:

- **Header** - metadata, e.g. `{"alg": "HS256", "typ": "JWT"}`
- **Payload** - claims, e.g. `{"sub": "123", "role": "admin", "exp": 1710000000}`
- **Signature** - cryptographic proof over `header.payload`.

Token format:

```text
header.payload.signature
```

Payload is just base64url-encoded JSON; it is **not** encrypted by default.

## 2. Symmetric JWT (HS256)

- One shared **secret** (random string) known only to trusted servers.
- Same secret is used to **sign** and **verify**.

**Signing (auth server):**

- Build header and payload JSON.
- Compute:

  ```text
  signature = HMAC_SHA256(base64url(header) + "." + base64url(payload), secret)
  ```

- Token = `headerB64.payloadB64.signatureB64`.
- Return token to client (header, JSON body, or cookie).

**Verification (API server):**

- Split token into `headerB64`, `payloadB64`, `sigB64`.
- Recompute:

  ```text
  expectedSig = HMAC_SHA256(headerB64 + "." + payloadB64, secret)
  ```

- Compare `expectedSig` with `sigB64`.
- If match and claims valid (`exp`, `aud`, etc.) -> accept.
- No DB required for this step.

**Who creates the secret?**

- Dev/infra generates it once (e.g., 256-bit random).
- Stored in env vars / secret manager.
- Never sent to clients.

**Who is the "auth server"?**

- The component that:
  - Validates login (password, OAuth, SSO).
  - Issues JWTs after successful auth.
- In a monolith, this is just your backend.
- In microservices, it might be a separate identity/auth service or a provider (Auth0, Cognito, Keycloak, etc.).

## 3. Asymmetric JWT (RS256 / ES256)

- **Private key**: kept secret on auth server, used to **sign**.
- **Public key**: shared with other services, used to **verify**.

**Signing (auth server):**

- Build header + payload.
- Use **private key** to sign `headerB64.payloadB64`.
- Send token to client.

**Verification (API services):**

- Receive token from client.
- Use **public key** to verify signature against `headerB64.payloadB64`.
- No private key, no DB needed.

**Why asymmetric?**

- Multiple services can verify tokens with just the public key.
- Private key stays isolated in auth/identity service.
- Easier for multi-tenant / external clients.

## 4. What the client actually does

- Client receives the already-signed token.
- Stores it (cookie / memory / localStorage).
- Sends it on each request:
  - `Authorization: Bearer <token>` or
  - As a cookie.

Client does **not**:

- Sign the token.
- Decrypt the token.
- Know any secrets or private keys.

The token is visible in browser devtools / network tab and can be copied. Security comes from:

- Signature (unforgeability).
- Expiry (`exp`).
- Possibly revocation lists / rotation.

## 5. Cookie usage with JWT

Common pattern in big apps:

- Put JWT in an **`HttpOnly` + `Secure`** cookie to reduce XSS exposure.
- Set `SameSite=Lax` or `Strict` to mitigate CSRF.
- Use short-lived access tokens; optionally pair with refresh tokens.
- On logout-all or compromise:
  - Rotate signing keys, or
  - Maintain a revocation list (`jti` blacklist) checked in a cache/DB.

## 6. Contrast: session cookies

**Session cookie approach:**

- Cookie holds a random **session ID**, not user data.
- Server keeps a `session_id -> session_data` map in Redis/DB.
- On each request:
  - Read session ID from cookie.
  - Lookup in session store to get user info.

**Differences vs JWT:**

- Sessions:
  - DB/cache lookup per request.
  - Easy revocation (delete row).
  - Simple mental model.
- JWT:
  - No lookup needed for basic verification (stateless).
  - Better for microservices and external APIs.
  - Revocation is harder; must rely on expiry / blacklists / key rotation.

## 7. TL;DR mental model

- **JWT (symmetric)**: all trusted servers share a secret; they sign and verify tokens using HMAC.
- **JWT (asymmetric)**: auth server signs with a private key; others verify with a public key.
- **Client**: just carries and re-sends the signed blob; token is visible but hard to forge.
- **Sessions**: cookie stores an ID; real data lives server-side in a session store.

If you want, we can next add a section to this doc with an end-to-end example flow (login -> set cookie -> call two microservices -> logout) for both JWT and classic sessions, so you have a full story in this same file.
