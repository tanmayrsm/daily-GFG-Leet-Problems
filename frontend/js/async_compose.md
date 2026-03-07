# Async Composition: Chaining Promises with Reduce

Async composition allows you to chain asynchronous functions together, similar to synchronous composition but handling promise resolution at each step.

## Basic Async Functions (Promises)

```js
const f1 = (val) => new Promise((res) => setTimeout(() => res(val * 2), 2000));
const f2 = (val) => new Promise((res) => setTimeout(() => res(val * 3), 4000));
const f3 = (val) => new Promise((res) => setTimeout(() => res(val * 4), 1000));
```

## Approach 1: Manual Promise Chaining with `.then()`

```js
const asyncCompose = (...args) => {
    return args.reduce((acc, item) => {
        return acc.then(item);  // Chain promises sequentially
    }, Promise.resolve(1));     // Start with initial Promise
};

// Usage
asyncCompose(f1, f2, f3).then(fin => console.log("Result:", fin));
// f1(1) → f2(result) → f3(result) → final result
```

**How it works:**
- `Promise.resolve(1)` creates an initial resolved promise with value `1`
- Each `acc.then(item)` chains the next function, passing the previous result to it
- The final promise resolves with the last function's result

## Approach 2: Async/Await with Reduce (Cleaner)

```js
const asyncCompose2 = (...fns) => {
    return fns.reduce(
        async (accPromise, fn) => {
            const acc = await accPromise;   // Unwrap previous promise
            const r = await fn(acc);        // Apply current function
            return r;                       // Return for next iteration
        },
        Promise.resolve(1)                  // Initial value
    );
};

// Usage
asyncCompose2(f1, f2, f3).then(fin => console.log("Result 2:", fin));
```

**Key difference from Approach 1:**
- Uses `async` function in the reducer
- Explicitly `await` the previous promise
- Explicitly `await` the function result
- More readable, especially for complex logic

## Execution Flow

With `asyncCompose(f1, f2, f3)` starting with value `1`:

```
Step 1: f1(1)   → wait 2s → returns 2
Step 2: f2(2)   → wait 4s → returns 6
Step 3: f3(6)   → wait 1s → returns 24
Result: 24
```

Total time: ~7 seconds (2s + 4s + 1s)

## Comparison: Synchronous vs Async Composition

### Synchronous Composition (from pipe_compose)
```js
const compose = (...args) => {
    return args.reduce((acc, item) => {
        let r = item(acc);      // Execute immediately
        return r;               // Next iteration uses this value
    }, 1);
};

// Result is immediate (no waiting)
const result = compose(f1, f2, f3);  // If f1, f2, f3 were sync functions
```

### Async Composition
```js
const asyncCompose = (...fns) => {
    return fns.reduce((acc, item) => {
        return acc.then(item);  // Chain promises
    }, Promise.resolve(1));
};

// Result is a Promise (needs .then() or await)
asyncCompose(f1, f2, f3).then(result => console.log(result));
```

## When to Use

- **Manual `.then()`**: Simple cases, minimal logic between chains
- **Async/await**: When you need to add intermediate logic or error handling
- **Use composition**: Avoid callback hell, make data flow explicit, reuse function pipelines
