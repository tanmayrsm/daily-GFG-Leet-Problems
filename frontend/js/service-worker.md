# Service Workers

## Overview

A **Service Worker** is a special type of worker that acts as a **programmable network proxy** between your app and the network. It enables **offline functionality**, **caching strategies**, and **push notifications**.

---

## Key Characteristics

| Feature | Service Worker |
|---------|-----------------|
| **Scope** | All pages under the scope |
| **Main Use** | Offline support, caching, push notifications |
| **Communication** | Message passing + network interception |
| **Lifetime** | Independent of page (persists after close) |
| **Access** | No DOM access; Full Fetch API access |
| **Registration** | Needs HTTPS (or localhost) |
| **Purpose** | Acts as network proxy for the app |

---

## When to Use Service Workers

✅ **Good Use Cases:**
- **Offline-first applications** (PWAs)
- **Background synchronization** (sync data when online)
- **Push notifications** (from server to user)
- **Advanced caching strategies** (cache-first, network-first, stale-while-revalidate)
- **Asset optimization** (preload critical resources)
- **Analytics/logging** (intercept and buffer requests)
- **Progressive Web Apps (PWAs)**

❌ **Bad Use Cases:**
- Quick operations (overhead too high)
- Frequent page reloads (not efficient)
- Simple offline support (consider simpler alternatives like IndexedDB)

---

## Service Worker Lifecycle

```
1. Register → 2. Install → 3. Activate → 4. Fetch/Message
```

### Detailed Lifecycle

```
┌─────────────┐
│  Register   │  Client registers SW
└──────┬──────┘
       │
       v
┌─────────────┐
│   Install   │  'install' event - can cache assets
└──────┬──────┘
       │
       v
┌─────────────┐
│  Activate   │  'activate' event - cleanup old caches
└──────┬──────┘
       │
       v
┌─────────────┐
│    Idle     │  Waiting for events
└──────┬──────┘
       │
   ┌───┴───────────────────────────┐
   │                               │
   v                               v
┌──────────┐              ┌──────────────┐
│  Fetch   │              │ Push/Message │
│ Intercept│              │   Events     │
└──────────┘              └──────────────┘
```

---

## Example: Basic Service Worker

### Client Code (main.js)
```javascript
// Register Service Worker
if ('serviceWorker' in navigator) {
  navigator.serviceWorker.register('/sw.js')
    .then(registration => {
      console.log('Service Worker registered:', registration);
    })
    .catch(error => {
      console.error('Service Worker registration failed:', error);
    });
}
```

### Service Worker (sw.js)
```javascript
const CACHE_NAME = 'cache-v1';
const urlsToCache = [
  '/',
  '/index.html',
  '/style.css',
  '/app.js'
];

// Install event - cache assets
self.addEventListener('install', (event) => {
  event.waitUntil(
    caches.open(CACHE_NAME)
      .then(cache => cache.addAll(urlsToCache))
      .then(() => self.skipWaiting()) // Activate immediately
  );
});

// Activate event - cleanup old caches
self.addEventListener('activate', (event) => {
  event.waitUntil(
    caches.keys().then(cacheNames => {
      return Promise.all(
        cacheNames.map(cacheName => {
          if (cacheName !== CACHE_NAME) {
            return caches.delete(cacheName);
          }
        })
      );
    }).then(() => self.clients.claim()) // Take control immediately
  );
});

// Fetch event - intercept network requests
self.addEventListener('fetch', (event) => {
  event.respondWith(
    caches.match(event.request)
      .then(response => {
        // Return cached response if available
        return response || fetch(event.request);
      })
      .catch(() => {
        // Offline fallback
        return new Response('Offline - content not available');
      })
  );
});
```

---

## Caching Strategies

### 1. **Cache-First (Cache, Falling Back to Network)**
Best for: Static assets that rarely change

```javascript
self.addEventListener('fetch', (event) => {
  event.respondWith(
    caches.match(event.request)
      .then(response => response || fetch(event.request))
  );
});
```

### 2. **Network-First (Network, Falling Back to Cache)**
Best for: Dynamic content that should be fresh

```javascript
self.addEventListener('fetch', (event) => {
  event.respondWith(
    fetch(event.request)
      .then(response => {
        // Cache successful responses
        const cache = caches.open('dynamic-cache');
        cache.then(c => c.put(event.request, response.clone()));
        return response;
      })
      .catch(() => caches.match(event.request))
  );
});
```

### 3. **Stale-While-Revalidate**
Best for: Content that can be slightly outdated

```javascript
self.addEventListener('fetch', (event) => {
  event.respondWith(
    caches.match(event.request)
      .then(response => {
        const fetchPromise = fetch(event.request)
          .then(freshResponse => {
            caches.open('dynamic-cache')
              .then(cache => cache.put(event.request, freshResponse.clone()));
            return freshResponse;
          });

        return response || fetchPromise;
      })
  );
});
```

### 4. **Network-Only**
Best for: Real-time data that must not be cached

```javascript
self.addEventListener('fetch', (event) => {
  if (event.request.url.includes('/api/')) {
    event.respondWith(fetch(event.request));
  }
});
```

### 5. **Cache-Only**
Best for: Bundled assets guaranteed to be cached

```javascript
self.addEventListener('fetch', (event) => {
  event.respondWith(caches.match(event.request));
});
```

---

## Real-World Example: PWA with Caching

```javascript
const CACHE_NAME = 'pwa-cache-v1';
const STATIC_ASSETS = [
  '/',
  '/index.html',
  '/offline.html',
  '/css/style.css',
  '/js/app.js',
  '/images/logo.png'
];

// Install: Cache static assets
self.addEventListener('install', (event) => {
  console.log('Installing Service Worker...');
  event.waitUntil(
    caches.open(CACHE_NAME)
      .then(cache => {
        console.log('Caching static assets');
        return cache.addAll(STATIC_ASSETS);
      })
      .then(() => self.skipWaiting())
  );
});

// Activate: Clean up old caches
self.addEventListener('activate', (event) => {
  console.log('Activating Service Worker...');
  event.waitUntil(
    caches.keys().then(cacheNames => {
      return Promise.all(
        cacheNames
          .filter(name => name !== CACHE_NAME)
          .map(name => caches.delete(name))
      );
    }).then(() => self.clients.claim())
  );
});

// Fetch: Smart caching strategy
self.addEventListener('fetch', (event) => {
  const { request } = event;
  const url = new URL(request.url);

  // API calls: Network first
  if (url.pathname.startsWith('/api/')) {
    event.respondWith(
      fetch(request)
        .then(response => {
          caches.open(CACHE_NAME).then(cache =>
            cache.put(request, response.clone())
          );
          return response;
        })
        .catch(() => caches.match(request))
    );
  }
  // Images: Cache first
  else if (request.destination === 'image') {
    event.respondWith(
      caches.match(request)
        .then(response => response || fetch(request))
    );
  }
  // Static assets: Cache first
  else {
    event.respondWith(
      caches.match(request)
        .then(response => response || fetch(request))
        .catch(() => caches.match('/offline.html'))
    );
  }
});

// Message handler: Update app from client
self.addEventListener('message', (event) => {
  if (event.data && event.data.type === 'SKIP_WAITING') {
    self.skipWaiting();
  }
});
```

---

## Push Notifications Example

### Server Side
```javascript
// Server sends push notification
const subscription = await db.getUserSubscription(userId);
webpush.sendNotification(subscription, {
  title: 'New Message',
  body: 'You have a new message!',
  icon: '/icon.png'
});
```

### Service Worker
```javascript
// Handle push events
self.addEventListener('push', (event) => {
  const data = event.data.json();

  event.waitUntil(
    self.registration.showNotification(data.title, {
      body: data.body,
      icon: data.icon,
      badge: data.badge
    })
  );
});

// Handle notification clicks
self.addEventListener('notificationclick', (event) => {
  event.notification.close();

  event.waitUntil(
    clients.matchAll({ type: 'window' }).then(clientList => {
      // Focus existing window if open
      for (let client of clientList) {
        if (client.url === '/' && 'focus' in client) {
          return client.focus();
        }
      }
      // Open new window if not open
      if (clients.openWindow) {
        return clients.openWindow('/');
      }
    })
  );
});
```

---

## Background Sync Example

### Client Code
```javascript
// Queue sync task when offline
async function syncData() {
  try {
    await fetch('/api/sync', { method: 'POST', body: JSON.stringify(data) });
  } catch (error) {
    // Queue for later sync
    if ('serviceWorker' in navigator) {
      const registration = await navigator.serviceWorker.ready;
      registration.sync.register('sync-data');
    }
  }
}
```

### Service Worker
```javascript
self.addEventListener('sync', (event) => {
  if (event.tag === 'sync-data') {
    event.waitUntil(
      fetch('/api/sync', {
        method: 'POST',
        body: JSON.stringify(queuedData)
      })
        .then(() => clearQueue())
        .catch(() => {
          // Retry later
          throw new Error('Sync failed');
        })
    );
  }
});
```

---

## Important Notes

⚠️ **HTTPS Required**:
- Service Workers only work over HTTPS (except localhost)
- This is for security reasons

⚠️ **Scope**:
- Service Worker can only control pages within its scope
- A SW at `/js/sw.js` can only control `/js/*` pages

⚠️ **Version Control**:
- Always version your caches (`cache-v1`, `cache-v2`, etc.)
- Clear old caches in the `activate` event

---

## **⚡ Differentiation from Web Workers**

| Aspect | Service Worker | Web Worker |
|--------|-----------------|-----------|
| **Purpose** | Offline support & caching | Heavy computation |
| **Scope** | All pages in domain scope | Single page only |
| **Lifetime** | Survives page close | Dies with page |
| **Network Proxy** | Yes (intercepts fetch) | No (message passing) |
| **DOM Access** | No | No |
| **Typical Use** | PWAs, offline-first | Image processing, math |
| **Registration** | `navigator.serviceWorker.register()` | `new Worker()` |

→ See [web-worker.md](web-worker.md) for full Web Worker details.

---

## Browser Support

✅ Service Workers supported in all modern browsers
❌ Not supported in IE 11 and older

---

## Debugging Service Workers

### Chrome DevTools
1. Open DevTools → Application tab
2. Look for "Service Workers" section
3. View registered SWs, their status, and cached resources
4. Manually trigger events (install, activate, etc.)

### Testing Offline
1. DevTools → Network tab
2. Check "Offline" checkbox
3. Test app behavior without network

---

## Performance Considerations

1. **Lazy registration**: Don't register immediately on page load
2. **Cache size limits**: Monitor storage quota
3. **Update strategy**: Implement a smart update check
4. **Precaching**: Only precache essential assets
5. **Network requests**: Batch and debounce API calls

---

## Update Strategy Pattern

```javascript
// Check for updates periodically
setInterval(() => {
  navigator.serviceWorker.getRegistrations().then(registrations => {
    registrations.forEach(registration => {
      registration.update(); // Check for new version
    });
  });
}, 60000); // Check every minute

// Listen for updates
navigator.serviceWorker.addEventListener('controllerchange', () => {
  console.log('New Service Worker activated!');
  // Reload page or notify user
  window.location.reload();
});
```
