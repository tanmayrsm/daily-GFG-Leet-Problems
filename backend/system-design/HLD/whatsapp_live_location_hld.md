# WhatsApp-Style Live Location – High Level Design

Focus: **how live location flows end-to-end**, including Kafka + WebSockets.

---

## 1. Main Idea

For each chat, live location is a **continuous stream**:

- Sender phone → Ingestion → Kafka → Fan-out → WebSocket → Viewer phone
- Plus a **current-location store** so late joiners see the latest point immediately.

You *don't* replay all missed points; you show **latest + realtime**.

---

## 2. Key Components (Conceptual)

- **Mobile Clients**
  - Sender: periodically sends GPS (e.g., every 5–10s) when live share is ON.
  - Viewer: subscribes to live location for the chat, shows markers on a map.

- **Session Service**
  - Manages "live location session" lifecycle (start, stop, expiry).
  - Knows which chats have which live sessions active.

- **Location Ingestion Service**
  - Receives location updates from senders.
  - Validates active session.
  - Updates "latest location" store.
  - Publishes events to Kafka.

- **Kafka (Streaming Bus)**
  - Topic, e.g., `live-location-updates`.
  - Carries events `{session_id, chat_id, user_id, lat, lon, ts}`.
  - Used to decouple ingestion from fan-out, and to handle spikes.

- **Fan-out / Realtime Service**
  - Consumes Kafka topic.
  - For each event, finds which WebSocket connections are watching that chat.
  - Sends updates to those connections.

- **WebSocket Gateway**
  - Maintains persistent connections to clients.
  - Tracks subscriptions: `chat_id → set(connection_ids)`.
  - Pushes location updates to subscribed clients.

- **Current Location Store (Hot)**
  - E.g., Redis or fast DB table.
  - For each active session: stores **latest** point (`lat, lon, ts`).
  - Used to give a new viewer initial location when they open chat.

- **History / Audit Store (Cold, optional)**
  - Stores sampled points over time (e.g., 1 point every few minutes) for audit/compliance.
  - Not used for live rendering.

---

## 3. Session Lifecycle

### 3.1 Start Live Location

1. User opens chat `C123` and taps "Share live location".
2. Client calls Session Service: "start live session for chat `C123`, duration X".
3. Session Service:
   - Creates a `session_id` (e.g., `S1`) tied to `chat_id = C123`, with `expires_at`.
   - Marks session status = ACTIVE.
4. Chat service sends a normal chat event: "A is sharing live location" (with `session_id=S1`) to all members of `C123`.

Now everyone knows that chat `C123` has an active live session `S1`.

---

### 3.2 Stop / Expire

- **Stop:**
  - Sender taps "Stop live location".
  - Client calls Session Service: stop `session_id=S1`.
  - Session Service marks it STOPPED and emits a chat event: "A stopped live location".

- **Auto Expiry:**
  - Background job periodically:
    - Finds sessions where `now > expires_at` and status=ACTIVE.
    - Marks them EXPIRED and cleans entries in hot store.

Clients stop drawing marker when:
- They get "stopped/expired" event, or
- No new updates for some timeout.

---

## 4. Sender Flow (Online, Live)

Sender A is sharing live location for `S1` in `C123`.

### 4.1 Phone → Ingestion

Every 5–10s:

1. Phone reads GPS.
2. Sends an update to Location Ingestion Service:
   - Could be HTTP or WebSocket frame:
   - `{ session_id: S1, lat, lon, ts }`.

### 4.2 Ingestion → Current Store + Kafka

Location Ingestion Service:

1. Checks with Session Service (or its cache):
   - `S1` is ACTIVE and not expired.
2. Updates **Current Location Store**:
   - `S1 → { lat, lon, ts }`.
3. Publishes an event to Kafka topic `live-location-updates`:
   ```json
   {
     "session_id": "S1",
     "chat_id": "C123",
     "sender_id": "A",
     "lat": 12.9,
     "lon": 77.6,
     "ts": 1739012345
   }
   ```

---

## 5. Viewer Flow (Online, Live)

Viewer B opens chat `C123` and sees "A is sharing live location".

### 5.1 Initial State

1. Client calls Session Service or Chat Service to get active sessions for `C123`.
   - Gets `session_id=S1`.
2. Client fetches **current location** from Current Location Store:
   - `S1 → { lat: 12.9, lon: 77.6, ts: ... }`.
3. Shows this initial marker on map.

### 5.2 Subscribe for Updates

1. Client subscribes via WebSocket Gateway:
   - `subscribe { chat_id: C123 }` (or `session_id: S1`).
2. WebSocket Gateway records:
   - `chat_id=C123 → { connection_ids: [B_conn, ...] }`.

### 5.3 Receive Realtime Updates

Fan-out Service consumes Kafka and pushes to WebSocket Gateway:

1. Kafka event arrives: `{ session_id: S1, chat_id: C123, lat: 12.91, lon: 77.61, ts: ... }`.
2. Fan-out checks WebSocket subscriptions for `C123`.
3. Sends update to B's connection.
4. B's client updates marker on map smoothly (interpolate or jump).

---

## 6. Kafka in the Middle

### Why Kafka?

- **Decouples** ingestion (write) from fan-out (read).
- **Buffers** during traffic spikes (many users sharing live location simultaneously).
- **Enables** horizontal scaling:
  - Multiple ingestion service instances write to topic.
  - Multiple fan-out consumer instances read from partitions.
- **Replay / Debugging**: If fan-out fails, can reprocess from offset (if needed).

### Partitioning

- Partition by `chat_id` or `session_id`:
  - All updates for one chat/session go to same partition.
  - Maintains order per session.
  - Fan-out consumers process partitions in parallel.

---

## 7. Late Joiner / Offline Viewer

### Scenario

Viewer C opens chat `C123` 5 minutes after A started sharing.

### What C Sees

1. C fetches **current location** from Current Location Store:
   - Gets latest point (not all past points).
2. C subscribes to WebSocket for future updates.
3. From that moment on, C gets realtime updates via Kafka → Fan-out → WebSocket.

C does **not** get a full trail of A's movement; just:
- Latest position + path going forward.

If you want to show a trail:
- Store last N points in Current Location Store (e.g., last 10 minutes).
- C fetches those on join and draws the recent path.

---

## 8. Offline Sender / Network Issues

### Sender Disconnects

- Phone stops sending updates.
- After some timeout (e.g., 30s of no updates):
  - Viewers see marker as "stale" or "last known".
  - Optionally, UI shows "Last seen X seconds ago".

### Sender Reconnects

- Phone resumes sending updates.
- Location Ingestion:
  - Checks session still ACTIVE and not expired.
  - Resumes publishing to Kafka.
- Viewers immediately see updated marker.

No complex "gap filling"; just resume from current point.

---

## 9. Scale Considerations

### High Load

- **Many senders**: Ingestion service scales horizontally; Kafka partitions handle throughput.
- **Many viewers**: WebSocket Gateway scales horizontally; each instance holds subset of connections.
- **Large groups**: If chat has 1000 members, fan-out must push to 1000 connections:
  - Use efficient WebSocket server (e.g., Netty, Go, Node.js).
  - Consider regional gateways to reduce cross-DC latency.

### Current Location Store (Redis)

- **TTL**: Auto-expire entries when session expires or stops.
- **Eviction**: Use LRU if memory tight; less critical data (old sessions) gets evicted.
- **Replication**: Redis Cluster or master-replica for HA.

---

## 10. Security & Privacy

### Authorization

- **Send location**: Only if user A is in chat `C123` and has permission.
- **View location**: Only members of `C123` can subscribe to `S1`.
- WebSocket Gateway checks:
  - User B in chat `C123`?
  - Session `S1` belongs to `C123`?
  - If not, reject subscription.

### End-to-End Encryption

- Live location is typically **not** E2E encrypted in practice (WhatsApp encrypts chat messages, but live location coordinates go through server).
- If you want E2E:
  - Encrypt `lat, lon` before sending.
  - Server relays encrypted blob.
  - Viewers decrypt with shared chat key.
  - Trade-off: server can't do geofencing / validation.

---

## 11. Diagram (Text)

```
[Sender Phone A]
    | (every 5-10s)
    v
[Location Ingestion Service]
    |
    +---> [Current Location Store (Redis)]
    |        session_id → {lat, lon, ts}
    |
    v
[Kafka: live-location-updates topic]
    |
    v
[Fan-out / Realtime Service]
    | (consumes Kafka)
    | (looks up subscriptions)
    v
[WebSocket Gateway]
    | (pushes to subscribed connections)
    v
[Viewer Phone B, C, ...]
```

**Session Lifecycle:**
```
[Session Service]
  - start session → creates session_id, expires_at
  - stop session → marks STOPPED
  - background job → expires old sessions
```

---

## 12. Summary for Interviews

> "For live location, I'd use a **Kafka-based streaming architecture**:
> - Sender phones push GPS updates to an ingestion service every few seconds.
> - Ingestion writes to a **hot Redis store** (latest point per session) and publishes to **Kafka**.
> - A **fan-out service** consumes Kafka and pushes updates via **WebSocket** to viewers subscribed to that chat.
> - Late joiners fetch the current location from Redis and then get realtime updates.
> - Sessions have a TTL and are managed by a Session Service, which handles start/stop/expiry.
> - This decouples write load from fan-out, scales horizontally, and handles spikes gracefully."

Key points:
- **Kafka** for decoupling and buffering.
- **Redis** for current state.
- **WebSocket** for realtime push to clients.
- **Session lifecycle** to manage active shares.
- **No full history** needed for live view; just latest + ongoing stream.
