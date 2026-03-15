# Web Workers

## Overview

A **Web Worker** is a JavaScript script that runs in the background, separate from the main thread. It enables **heavy computations** to run without blocking the UI.

---

## Key Characteristics

| Feature | Web Worker |
|---------|-----------|
| **Scope** | Separate global scope (no access to DOM) |
| **Main Use** | Offload CPU-intensive tasks (calculations, parsing, etc.) |
| **Communication** | Message passing (postMessage / onmessage) |
| **Lifetime** | Lives as long as you keep it alive; explicitly terminated |
| **Access** | No DOM, Window, Parent object access |
| **Debugging** | Harder to debug (separate execution context) |
| **Performance Impact** | Reduces main thread blocking |

---

## When to Use Web Workers

✅ **Good Use Cases:**
- Heavy computations (image processing, cryptography)
- Large data processing (sorting, filtering massive arrays)
- Parsing large JSON/XML files
- Real-time calculations (physics simulations, ML inference)
- Data compression/decompression
- Background image manipulation

❌ **Bad Use Cases:**
- DOM manipulation (workers can't access DOM)
- Quick operations (overhead of worker creation > benefit)
- Frequent message passing between threads
- UI updates (must message back to main thread)

---

## Example: Basic Web Worker

### Main Thread (main.js)
```javascript
// Create a worker
const worker = new Worker('worker.js');

// Send data to worker
worker.postMessage({
  numbers: [1, 2, 3, 4, 5]
});

// Receive result from worker
worker.onmessage = (event) => {
  console.log('Result from worker:', event.data);
  // Output: Result from worker: 15 (sum)
};

// Handle errors
worker.onerror = (error) => {
  console.error('Worker error:', error.message);
};

// Terminate worker when done
// worker.terminate();
```

### Worker Thread (worker.js)
```javascript
// Listen for messages from main thread
self.onmessage = (event) => {
  const numbers = event.data.numbers;

  // Perform heavy computation
  const sum = numbers.reduce((a, b) => a + b, 0);

  // Send result back to main thread
  self.postMessage({
    result: sum,
    status: 'complete'
  });
};
```

---

## Example: Image Processing Worker

### Main Thread
```javascript
const worker = new Worker('image-worker.js');

// Process image asynchronously
function processImage(imageData) {
  worker.postMessage({ imageData });

  worker.onmessage = (event) => {
    const processedData = event.data;
    // Update UI with processed image
    renderImage(processedData);
  };
}

// Load and process large image without freezing UI
const canvas = document.getElementById('canvas');
const ctx = canvas.getContext('2d');
const img = new Image();
img.onload = () => {
  ctx.drawImage(img, 0, 0);
  const imageData = ctx.getImageData(0, 0, canvas.width, canvas.height);
  processImage(imageData);
};
img.src = 'large-image.jpg';
```

### Worker Thread (image-worker.js)
```javascript
self.onmessage = (event) => {
  const imageData = event.data.imageData;
  const data = imageData.data;

  // Apply grayscale filter (CPU-intensive)
  for (let i = 0; i < data.length; i += 4) {
    const gray = data[i] * 0.299 + data[i + 1] * 0.587 + data[i + 2] * 0.114;
    data[i] = gray;       // R
    data[i + 1] = gray;   // G
    data[i + 2] = gray;   // B
  }

  self.postMessage(imageData);
};
```

---

## Advanced Usage

### Shared Worker
Multiple tabs can share a single worker instance:

```javascript
// Can be used by multiple pages
const sharedWorker = new SharedWorker('shared-worker.js');
sharedWorker.port.start();
sharedWorker.port.postMessage('Hello from page 1');
sharedWorker.port.onmessage = (event) => {
  console.log(event.data);
};
```

### Worker Pool
Create multiple workers for better performance:

```javascript
class WorkerPool {
  constructor(scriptPath, poolSize = 4) {
    this.workers = [];
    this.taskQueue = [];
    this.activeWorkers = new Set();

    for (let i = 0; i < poolSize; i++) {
      const worker = new Worker(scriptPath);
      worker.onmessage = (e) => {
        this.activeWorkers.delete(worker);
        this.processNext();
      };
      this.workers.push(worker);
    }
  }

  execute(data) {
    return new Promise((resolve) => {
      this.taskQueue.push({ data, resolve });
      this.processNext();
    });
  }

  processNext() {
    if (this.taskQueue.length === 0 || this.activeWorkers.size >= this.workers.length) {
      return;
    }

    const availableWorker = this.workers.find(w => !this.activeWorkers.has(w));
    if (!availableWorker) return;

    const { data, resolve } = this.taskQueue.shift();
    this.activeWorkers.add(availableWorker);

    availableWorker.onmessage = (e) => {
      resolve(e.data);
      this.activeWorkers.delete(availableWorker);
      this.processNext();
    };

    availableWorker.postMessage(data);
  }
}

// Usage
const pool = new WorkerPool('heavy-task-worker.js', 4);
pool.execute({ numbers: [1, 2, 3] }).then(result => {
  console.log('Task 1 result:', result);
});
```

---

## Limitations

⚠️ **Cannot Access:**
- DOM elements
- Window object
- Parent object
- document object

⚠️ **Can Access:**
- navigator object
- XMLHttpRequest / fetch API
- setTimeout / setInterval
- Application Cache

---

## Browser Support

✅ Supported in all modern browsers (Chrome, Firefox, Safari, Edge)

---

## Debugging Tips

1. **Chrome DevTools**: Open DevTools → Sources tab → find "Workers" section
2. **Log messages**: Use `console.log()` in worker (visible in DevTools)
3. **Error handling**: Always add `.onerror` handler
4. **Message inspection**: Log `event.data` to understand message flow

---

## **⚡ Differentiation from Service Workers**

| Aspect | Web Worker | Service Worker |
|--------|-----------|-----------------|
| **Purpose** | Offload computation | Cache & offline support |
| **Scope** | Single page only | All pages in scope |
| **Lifetime** | Page lifetime | Independent of pages |
| **Network Access** | Limited | Full (Fetch API) |
| **Storage** | IndexedDB, LocalStorage | IndexedDB, CacheStorage |
| **Use Case** | Heavy math, image processing | Offline-first apps, push notifications |

→ See [service-worker.md](service-worker.md) for full Service Worker details.

---

## Performance Tips

1. **Reuse workers**: Don't create new workers for every task
2. **Batch operations**: Send large chunks of data, not one item at a time
3. **Use TypedArrays**: For large numerical data, use `ArrayBuffer`, `Uint8Array`
4. **Terminate when done**: Free up resources with `worker.terminate()`
5. **Monitor memory**: Workers consume memory; don't spawn too many

---

## Real-World Example: Data Processing Pipeline

```javascript
// Main thread
class DataProcessor {
  constructor() {
    this.workers = [
      new Worker('parse-worker.js'),
      new Worker('analyze-worker.js'),
      new Worker('compress-worker.js')
    ];
  }

  async processPipeline(rawData) {
    // Step 1: Parse
    const parsed = await this.sendToWorker(this.workers[0], rawData);

    // Step 2: Analyze
    const analyzed = await this.sendToWorker(this.workers[1], parsed);

    // Step 3: Compress
    const final = await this.sendToWorker(this.workers[2], analyzed);

    return final;
  }

  sendToWorker(worker, data) {
    return new Promise((resolve) => {
      worker.postMessage(data);
      worker.onmessage = (e) => resolve(e.data);
    });
  }
}

// Usage
const processor = new DataProcessor();
processor.processPipeline(hugeDataset).then(result => {
  console.log('Processing complete:', result);
});
```
