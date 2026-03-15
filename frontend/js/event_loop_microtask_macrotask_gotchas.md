Global order is: current script (sync) -> all microtasks -> rendering -> next macrotask. Promises sit in the microtask queue; timers, intervals, I/O, UI events are macrotasks. [javascript](https://javascript.info/event-loop)

***

## Global event-loop order (browser-ish)

Rough loop:

1. Run one macrotask (often: your `<script>` or an event handler). [stackoverflow](https://stackoverflow.com/questions/25915634/difference-between-microtask-and-macrotask-within-an-event-loop-context)
2. When the call stack is empty, run all microtasks (FIFO). [geeksforgeeks](https://www.geeksforgeeks.org/javascript/what-are-the-microtask-and-macrotask-within-an-event-loop-in-javascript/)
3. Optionally render (paint). [linkedin](https://www.linkedin.com/posts/piyush-eon_%F0%9D%97%A2%F0%9D%97%BB%F0%9D%97%B2-%F0%9D%97%98%F0%9D%98%83%F0%9D%97%B2%F0%9D%97%BB%F0%9D%98%81-%F0%9D%97%9F%F0%9D%97%BC%F0%9D%97%BC%F0%9D%97%BD-%F0%9D%97%B1%F0%9D%97%B2%F0%9D%98%81%F0%9D%97%AE%F0%9D%97%B6%F0%9D%97%B9-%F0%9D%97%B1%F0%9D%97%B2%F0%9D%97%B0%F0%9D%97%B6%F0%9D%97%B1%F0%9D%97%B2%F0%9D%98%80-activity-7414669857591418882-x-S7)
4. Pick next macrotask and repeat. [javascript](https://javascript.info/event-loop)

***

## What counts as what

- Macrotasks: initial script, `setTimeout`, `setInterval`, DOM events, message events, I/O, etc. [youtube](https://www.youtube.com/watch?v=YiDzk6hO37U)
- Microtasks: `Promise.then/catch/finally`, `queueMicrotask`, `MutationObserver`, `process.nextTick` (Node). [ankitjoshi.hashnode](https://ankitjoshi.hashnode.dev/event-loop-microtask-and-macro-task)

Your mental model should be:

> One macrotask -> drain microtasks -> maybe render -> next macrotask. [linkedin](https://www.linkedin.com/pulse/understanding-call-stack-microtask-queue-macrotask-javascript-jha-47euc)

***

## Canonical ordering example

```js
console.log(1);

setTimeout(() => console.log(2), 0);

Promise.resolve().then(() => console.log(3));

Promise.resolve().then(() => {
  console.log(4);
  setTimeout(() => console.log(5), 0);
});

console.log(6);
```

- Sync (same macrotask): `1, 6`. [stackoverflow](https://stackoverflow.com/questions/25915634/difference-between-microtask-and-macrotask-within-an-event-loop-context)
- Microtasks: `3, 4`. [geeksforgeeks](https://www.geeksforgeeks.org/javascript/what-are-the-microtask-and-macrotask-within-an-event-loop-in-javascript/)
- Macrotasks (timers): `2, 5` (order between timers with same delay is by enqueue time). [javascript](https://javascript.info/event-loop)

Output: `1 6 3 4 2 5`. [javascript](https://javascript.info/event-loop)

***

## How your promise fits

```js
console.log(1);
const promise = new Promise((resolve) => {
  console.log(2);  // sync, runs during current macrotask
  resolve();       // schedules microtasks (if any .then)
  console.log(3);  // sync
});
console.log(4);
```

- `1` logs.
- `new Promise` immediately calls the executor, so `2` and `3` log synchronously in place. [developer.mozilla](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Promise)
- `4` logs after the constructor returns.
No `.then` here, so no microtasks to show.

If you add a `.then`:

```js
promise.then(() => console.log(5));
```

Then order is: `1 2 3 4 5` (because `5` is a microtask after the current macrotask finishes). [digitalocean](https://www.digitalocean.com/community/tutorials/understanding-the-event-loop-callbacks-promises-and-async-await-in-javascript)

***

## Edge-case patterns and their order

### Many microtasks vs timers

```js
console.log('A');

setTimeout(() => console.log('B'), 0);

Promise.resolve().then(() => console.log('C'));
Promise.resolve().then(() => {
  console.log('D');
  queueMicrotask(() => console.log('E'));
});

console.log('F');
```

Order:

- Sync: `A F`.
- Microtasks (FIFO, including ones queued by microtasks): `C D E`. [dev](https://dev.to/jeetvora331/difference-between-microtask-and-macrotask-queue-in-the-event-loop-4i4i)
- Macrotasks: `B`. [stackoverflow](https://stackoverflow.com/questions/25915634/difference-between-microtask-and-macrotask-within-an-event-loop-context)

So: `A F C D E B`.

### Microtasks can starve macrotasks

You can (badly) loop microtasks so timers never run:

```js
function spam() {
  Promise.resolve().then(spam);
}
spam();

setTimeout(() => console.log('never?'), 0);
```

Microtasks keep refilling themselves, so the event loop keeps draining them and never reaches the macrotask queue. [javascript.plainenglish](https://javascript.plainenglish.io/microtasks-vs-macrotasks-the-confusion-behind-settimeout-5532c2d7a3db)

***

## Node-specific extras

In Node, after each phase:

- `process.nextTick` queue runs before normal microtasks. [dev](https://dev.to/ynmanware/setimmediate-settimeout-and-process-nexttick-3mfd)
- Then microtasks (promise callbacks).
- Then the loop moves to the next phase (`timers`, `poll`, `check`, etc.). [nodejs](https://nodejs.org/en/learn/asynchronous-work/event-loop-timers-and-nexttick)

Rough priority in Node: current stack -> `process.nextTick` -> promise microtasks -> rest of the phase -> next phase/macrotasks. [greatfrontend](https://www.greatfrontend.com/questions/quiz/what-is-the-difference-between-settimeout-setimmediate-and-processnexttick)
