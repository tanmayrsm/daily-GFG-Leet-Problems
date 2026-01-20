Below is a concise `security-notes.md` you can reuse and extend.

```markdown
# Web Security Notes – JWT, OAuth, Tokens & Attacks

## 1. Core Concepts

### JWT (JSON Web Token)

- Compact, URL‑safe token format used to represent claims (user id, roles, expiry, etc.).
- Structure: `header.payload.signature` (all Base64URL encoded).
- Header: algorithm (`alg`), type (`typ`).
- Payload: claims (e.g., `sub`, `iat`, `exp`, `scope`).
- Signature: proves integrity; server verifies to detect tampering.
- Typically used as **access tokens** or **ID tokens** in stateless authentication.

### Access Token

- Short‑lived token used on each protected API call (e.g., in `Authorization: Bearer <token>`).
- Encodes “who you are” and “what you can do” (scopes/roles).
- If stolen, attacker can act as user until it expires → must be short‑lived and scoped.

### Refresh Token

- Long‑lived token used only to get new access tokens when they expire.
- Never sent on normal business APIs; only to `/auth/refresh` or `/oauth/token`.
- Stored more securely (e.g., HttpOnly Secure SameSite cookie + DB record).
- Often rotated (new refresh token issued and old one invalidated) to limit replay.

### “Auth Token”

- Generic term for any token used to authenticate/authorize (session ID, JWT, opaque token).
- In code, often just means “the access token the client sends to call APIs”.

---

## 2. OAuth 2.0 Overview

### What OAuth Solves

- Standard protocol so one app (Client) can access resources on a server on behalf of a user without learning the user’s password.
- Typical example: “Login with Google / GitHub”.

### Roles

- **Resource Owner**: User.
- **Client**: Your app (SPA, mobile, backend).
- **Authorization Server**: Issues tokens after authenticating and getting consent.
- **Resource Server**: APIs that accept access tokens and serve data.

### Typical OAuth 2.0 Web Flow (Auth Code + PKCE)

1. Client redirects browser to Authorization Server `/authorize` with:
   - `client_id`, `redirect_uri`, `scope`, `code_challenge`, `response_type=code`.
2. User logs in + consents.
3. Authorization Server redirects back to client with `code`.
4. Backend exchanges `code` + `code_verifier` at `/token` for:
   - `access_token`, `refresh_token` (optional), `id_token` (OpenID Connect).

---

## 3. Token Storage & Security

### Storage Options (Client Side)

- **HttpOnly Secure SameSite Cookie**
  - JS cannot read it (helps against token theft via XSS).
  - Sent automatically with matching requests → must handle CSRF.
  - Ideal for refresh tokens and sometimes session IDs.

- **localStorage / sessionStorage**
  - JS‑readable; not auto‑sent; no built‑in CSRF risk.
  - Vulnerable to XSS token theft; better for less sensitive, short‑lived data.

- **In‑memory (JS variable, React context)**
  - Not persisted; lost on reload → better against persistent theft.
  - Still readable by any injected script during that tab’s lifetime.
  - Often combined with silent refresh or bootstrap from secure cookie.

### Common Pattern

- Access token: short‑lived, stored **in memory** and sent via `Authorization` header.
- Refresh token: long‑lived, stored in **HttpOnly Secure SameSite cookie** + DB record.

---

## 4. Threats & Attacks

### XSS (Cross‑Site Scripting)

- Attacker injects JS into your pages (via vulnerable inputs, unsafe rendering, etc).
- With XSS, attacker can:
  - Read JS‑accessible tokens (localStorage, non‑HttpOnly cookies, in‑memory).
  - Call APIs as the user from the victim’s browser.
- HttpOnly cookies help prevent **stealing** tokens via `document.cookie`, but XSS can still perform actions as the user (session riding).

**Mitigations**

- Strong output encoding/escaping.
- Avoid dangerous sinks (`innerHTML`, `eval`, etc.).
- Content Security Policy (CSP).
- Prefer HttpOnly cookies or in‑memory for sensitive tokens.

### CSRF (Cross‑Site Request Forgery)

- Browser auto‑sends cookies on cross‑site requests.
- Malicious site tricks user’s browser into calling your site (form POST, fetch), and cookies get attached → actions run as user without their intention.

**Mitigations**

- `SameSite=Lax` or `Strict` on auth cookies.
- CSRF tokens (per form or per session).
- Double‑submit cookie pattern.
- Check `Origin` / `Referer` headers.

### Token Theft

Ways tokens can be stolen:

- Via XSS (JS‑readable tokens).
- Over insecure HTTP (no TLS).
- Via browser extensions or malware.
- Logging and error pages accidentally printing tokens.

**Mitigations**

- Always HTTPS.
- Short token lifetimes.
- HttpOnly+Secure cookies for refresh.
- No tokens in URLs or logs.
- Scope tokens narrowly (least privilege).

---

## 5. Access + Refresh Flow Between Client & Server

Short version of the lifecycle: **Login → Access token + Refresh token → Use access token → Refresh when expired → Logout/revocation**.

### Text Diagram – End‑to‑End Flow

```text
 Login [stackoverflow](https://stackoverflow.com/questions/70643645/session-vs-access-token-refresh-token)
    Client -> POST /auth/login (credentials)
    Server:
      - Validates credentials
      - Issues:
          Access token (short-lived, e.g., 15m)
          Refresh token (long-lived, e.g., 7d)
      - Response:
          JSON { accessToken: "..." }
          Set-Cookie: refreshToken=...; HttpOnly; Secure; SameSite=Lax

    Client:
      - Stores access token in memory (e.g., React state)
      - Browser stores refresh token in HttpOnly cookie

 Using the API (while access token valid) [authgear](https://www.authgear.com/post/session-vs-token-authentication)
    Client -> GET /api/cart
        Authorization: Bearer <ACCESS_TOKEN>
    Server:
      - Verifies access token (signature, exp, etc.)
      - Authorizes user and processes request
      - Returns data

 Access token expired [reddit](https://www.reddit.com/r/node/comments/1aox0au/whats_the_ultimate_resource_for_jwt_vs_session/)
    Case A: Client checks exp and sees it is expired/near expiry
    Case B: API responds 401 "token expired" to a call

 Refresh flow [geeksforgeeks](https://www.geeksforgeeks.org/javascript/access-token-vs-refresh-token-a-breakdown/)
    Client -> POST /auth/refresh
        (no token in body; browser automatically sends refresh cookie)
    Server:
      - Reads refresh token from Cookie header
      - Validates it:
          * Check signature (if JWT) OR lookup by jti/value in DB
          * Check not expired, not revoked, matches user/device
      - If valid:
          * Issues new access token (and maybe new refresh token)
          * Optionally rotates refresh token + DB record
      - Response:
          JSON { accessToken: "..." }
          (Optionally: Set-Cookie: new refreshToken=...; HttpOnly; Secure)

    Client:
      - Replaces in-memory access token with new one
      - Retries original failed API call with fresh token

 Logout / revocation [fusionauth](https://fusionauth.io/docs/apis/jwt)
    Client -> POST /auth/logout
    Server:
      - Marks refresh token as revoked or deletes DB row
      - Clears refresh cookie (Set-Cookie with Max-Age=0)
      - Further refresh attempts fail; user must log in again
```

---

## 6. Interview‑Style Summary Snippets

You can adapt these as “ready answers”.

### JWT + Access/Refresh

> “JWT is a signed token that encodes user claims and expiry. I use short‑lived JWT access tokens for each API call and long‑lived refresh tokens, stored in HttpOnly Secure SameSite cookies and tracked in DB, to mint new access tokens when they expire.”

### OAuth

> “OAuth 2.0 is the protocol for issuing access and refresh tokens. The client gets an authorization code from the authorization server, exchanges it for tokens at the token endpoint, then uses the access token to call resource servers. The refresh token is used later to get new access tokens without re‑authenticating the user.”

### Security & Attacks

> “Main risks are XSS, CSRF, and token theft. HttpOnly cookies protect tokens from direct XSS exfiltration, SameSite/CSRF tokens protect cookie‑based flows from CSRF, and short‑lived access tokens plus strongly protected refresh tokens limit the attack window if a token is compromised.”

```





Refresh flow[4]
    Client -> POST /auth/refresh
        (no token in body; browser automatically sends refresh cookie)
didnt u said refresh is httponly, so browser has nothing for this? also cant client repeatedly ask for refresh token ?


Good questions—this is exactly the nuance interviewers test.

***

## 1. “If it’s HttpOnly, how is it sent on /auth/refresh?”

**HttpOnly** only blocks **JavaScript** from reading/writing the cookie (`document.cookie`), it does **not** stop the browser from attaching the cookie to HTTP requests. [developer.mozilla](https://developer.mozilla.org/en-US/docs/Web/HTTP/Guides/Cookies)

So in this call:

```text
Client -> POST /auth/refresh
```

what happens is:

- Your JS does something simple like:

  ```js
  fetch('/auth/refresh', { method: 'POST', credentials: 'include' });
  ```

- The browser sees:
  - Request is going to `your-api.com`
  - There is a cookie `refreshToken=...; HttpOnly; Secure; SameSite=Lax` for that domain/path
- Browser automatically adds:

  ```http
  Cookie: refreshToken=eyJ...
  ```

- JS never sees the value; only the browser and the server “see” it. [en.wikipedia](https://en.wikipedia.org/wiki/HTTP_cookie)

So: **HttpOnly = hidden from JS, not from HTTP.** The refresh flow still works because the cookie is attached at HTTP level.

***

## 2. “Can the client keep calling /auth/refresh repeatedly?”

Yes, the client *can* call `/auth/refresh` many times—but you design the server so that abuse is limited and doesn’t give an infinite session:

### What the server does to control this

Common protections:

- **Expiry on refresh token**  
  - Each refresh token has its own `expiresAt` (e.g., 7 days).  
  - After that, `/auth/refresh` always returns 401/403 → user must log in again.

- **Rotation & single‑use**  
  - Every successful refresh:
    - Creates a new refresh token.
    - Invalidates the old one in DB.
  - If an old token is used again, server rejects or treats as suspicious (possible theft).

- **Rate limiting**  
  - Limit how often a given refresh token / user / IP can hit `/auth/refresh`.  
  - Example: “Not more than once every N seconds, and small daily cap”.

- **Session version / device binding**  
  - Refresh token row in DB has extra fields: `userId`, `jti`, `deviceId`, maybe last IP/user‑agent.  
  - Server can revoke individual tokens, all tokens for a user, or detect weird patterns.

So even if frontend code **spams** `/auth/refresh`, the server only issues new access tokens **while**:

- The refresh token is not expired.  
- It is not revoked.  
- Rate limits and security checks pass.

***

## 3. How to phrase this in interviews

You can say something like:

> “Even though the refresh token is HttpOnly, the browser still sends it as a cookie on `/auth/refresh`, so the backend can read it from the Cookie header. JavaScript never sees it. The client *could* call `/auth/refresh` often, but the server enforces expiry, rotation, and rate limiting so the refresh token can’t be abused indefinitely.”