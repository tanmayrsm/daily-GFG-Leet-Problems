WebSockets are best for two‑way, low‑latency communication; Server‑Sent Events (SSE) are simpler, one‑way from server to browser. HTTP/2 is a newer version of HTTP that sends data more efficiently than HTTP/1.1, and HTTPS is just HTTP running inside an encrypted tunnel so others on the network cannot read or modify traffic easily. [freecodecamp](https://www.freecodecamp.org/news/rendering-patterns/)

***

## WebSockets vs SSE

### WebSockets – advantages

- **Full duplex**: server and client can send messages anytime (chat, multiplayer games, live collaboration). [freecodecamp](https://www.freecodecamp.org/news/rendering-patterns/)
- Very low overhead once connected; messages are lightweight frames rather than full HTTP requests. [ehosseini](https://ehosseini.info/articles/rendering-strategies-comparison/)
- Works with binary data (e.g., protobuf, images, custom binary protocols). [ehosseini](https://ehosseini.info/articles/rendering-strategies-comparison/)

### WebSockets – disadvantages

- More complex to implement and scale (custom protocol on top of TCP, need stateful server or special infrastructure). [freecodecamp](https://www.freecodecamp.org/news/rendering-patterns/)
- Harder to cache, debug, and integrate with classic HTTP tools like proxies and CDNs. [ehosseini](https://ehosseini.info/articles/rendering-strategies-comparison/)
- Some environments block or restrict WebSocket upgrades (older proxies, strict firewalls). [ehosseini](https://ehosseini.info/articles/rendering-strategies-comparison/)

### SSE (Server‑Sent Events) – advantages

- Simple: just an HTTP connection where the server continuously **pushes** text events to the browser (`EventSource`). [freecodecamp](https://www.freecodecamp.org/news/rendering-patterns/)
- Uses normal HTTP semantics, so it usually plays nicely with proxies, load balancers, and is easy to debug. [appwrite](https://appwrite.io/blog/post/csr-ssg-ssr)
- Auto‑reconnect and last‑event‑ID support are built in at the browser level. [freecodecamp](https://www.freecodecamp.org/news/rendering-patterns/)

### SSE – disadvantages

- One‑way only: server → client; the client must use regular HTTP/Fetch/Ajax to send data back. [freecodecamp](https://www.freecodecamp.org/news/rendering-patterns/)
- Text only (UTF‑8); binary must be encoded (e.g., base64), which adds overhead. [appwrite](https://appwrite.io/blog/post/csr-ssg-ssr)
- Uses one long‑lived HTTP connection per client, which can be limiting for very high scale on some servers. [ehosseini](https://ehosseini.info/articles/rendering-strategies-comparison/)
