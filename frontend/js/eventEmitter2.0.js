class EventEmitter {
  constructor() {
    // Map eventName -> array of listeners
    this.events = {};
  }

  on(event, listener) {
    if (!this.events[event]) {
      this.events[event] = [];
    }
    this.events[event].push(listener);
    return this; // for chaining
  }

  off(event, listener) {
    if (!this.events[event]) return this;
    this.events[event] = this.events[event].filter((l) => l !== listener);
    return this;
  }

  emit(event, ...args) {
    if (!this.events[event]) return false;
    // Copy to avoid issues if listeners modify the array
    const listeners = [...this.events[event]];
    for (const listener of listeners) {
      listener(...args);
    }
    return true;
  }

  once(event, listener) {
    const wrapper = (...args) => {
      this.off(event, wrapper);
      listener(...args);
    };
    this.on(event, wrapper);
    return this;
  }
}


const emitter = new EventEmitter();

function onData(data) {
  console.log("data:", data);
}

emitter.on("data", onData);

emitter.emit("data", 1);  // logs: data: 1
emitter.emit("data", 2);  // logs: data: 2

emitter.off("data", onData);

emitter.emit("data", 3);  // no log

emitter.once("end", (msg) => {
  console.log("end:", msg);
});

emitter.emit("end", "first");  // logs: end: first
emitter.emit("end", "second"); // no log


/* 
what 'once' actually do ?

`emitter.once(event, listener)` (or `eventEmitter.once`) adds a listener that will run **only the next time** that event is emitted, and then it removes itself automatically.

Conceptually:

- First `emit("foo")` → listener runs, then is removed.  
- Any later `emit("foo")` → that listener is not called again.

In the simple implementation from before:

```js
once(event, listener) {
  const wrapper = (...args) => {
    this.off(event, wrapper);   // remove after first call
    listener(...args);          // call your original listener
  };
  this.on(event, wrapper);
  return this;
}
```

Here:

- `wrapper` is what actually gets stored in the listeners array.  
- On the first `emit(event)`, `wrapper` runs:
  - It removes itself (`off(event, wrapper)`),  
  - Then calls your original `listener`.  
- On later `emit(event)`, `wrapper` is no longer in the array, so it never runs again. 

*/

/*
EXAMPLE -

Here’s a small example showing how `once` behaves differently from `on`.

```js
const emitter = new EventEmitter();

emitter.on("tick", (value) => {
  console.log("on tick:", value);
});

emitter.once("tick", (value) => {
  console.log("once tick:", value);
});

emitter.emit("tick", 1);
emitter.emit("tick", 2);
emitter.emit("tick", 3);
```

Output:

- First `emit("tick", 1)`  
  - `on` listener runs:   `on tick: 1`  
  - `once` listener runs: `once tick: 1` (and then is removed)

- Second `emit("tick", 2)`  
  - `on` listener runs:   `on tick: 2`  
  - `once` listener does **not** run

- Third `emit("tick", 3)`  
  - `on` listener runs:   `on tick: 3`  

So you see `"once tick: ..."` only for the **first** emission, while `"on tick: ..."` logs every time. [nodejs](https://nodejs.org/en/learn/asynchronous-work/the-nodejs-event-emitter)

*/