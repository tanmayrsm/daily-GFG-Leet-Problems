Think of the browser’s order like this:

1. **All synchronous code** (top to bottom).  
2. Then **all microtasks**: `Promise.then`, `queueMicrotask`, etc.  
3. Then **animation frame callbacks**: `requestAnimationFrame` (before the next paint).  
4. Then **macrotasks**: `setTimeout`, `setInterval`, etc.  
Then the loop repeats. [mimo](https://mimo.org/glossary/javascript/event-loop)

To see this step by step, imagine this snippet:

```js
console.log("A");

setTimeout(() => {
  console.log("timeout");
}, 0);

Promise.resolve().then(() => {
  console.log("promise");
});

queueMicrotask(() => {
  console.log("microtask");
});

requestAnimationFrame(() => {
  console.log("raf");
});

console.log("B");
```

### Step 1: Run synchronous code

Executed immediately, in order:

- `console.log("A");`  
- `setTimeout(...)` is registered (its callback is scheduled as a macrotask, **not** run now).  
- `Promise.resolve().then(...)` schedules a microtask.  
- `queueMicrotask(...)` schedules another microtask.  
- `requestAnimationFrame(...)` schedules an animation callback.  
- `console.log("B");`

So, first logs:

1. `A`  
2. `B`  

### Step 2: Run all microtasks

After the synchronous script finishes, the engine drains the **microtask queue** before doing anything else. [interview-cheatsheet.netlify](https://interview-cheatsheet.netlify.app/docs/backend/eventloop/)

Microtasks in queue (in this snippet):

- The `Promise.then` callback  
- The `queueMicrotask` callback  

They run in the order they were queued, so:

3. `promise`  
4. `microtask`  

### Step 3: Run requestAnimationFrame callbacks

Before the next repaint, the browser runs **all** `requestAnimationFrame` callbacks that were scheduled. [dev](https://dev.to/atheodosiou/async-in-javascript-served-hot-the-chefs-guide-to-the-event-loop-536a)

5. `raf`  

### Step 4: Run macrotasks (timers)

Finally, the event loop takes the next macrotask from the task/timer queue, such as `setTimeout(..., 0)`. [mimo](https://mimo.org/glossary/javascript/event-loop)

6. `timeout`  

### Final log order for that snippet

- `A`  
- `B`  
- `promise`  
- `microtask`  
- `raf`  
- `timeout`  

Change the snippet however you like, and you can apply the same rules:

- **Sync first**  
- Then **all microtasks** (`Promise.then`, `queueMicrotask`)  
- Then **animation frame** (`requestAnimationFrame`)  
- Then **timers/tasks** (`setTimeout`)