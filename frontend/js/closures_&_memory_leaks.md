# JavaScript Closures and Memory Leaks in SPAs

This document explains:

- What closures are and how they work  
- How closures can keep data in memory  
- How that can cause memory leaks in long‑lived Single‑Page Applications (SPAs)  
- Practical examples (including a router diagram) and how to avoid leaks  

***

## 1. What is a closure?

A **closure** is a function bundled together with its surrounding variables (its lexical environment). Closures appear whenever an inner function uses variables defined in an outer function after that outer function has already returned. [developer.mozilla](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Guide/Closures)

```js
function outer() {
  let x = 10;
  return function inner() {
    console.log(x);
  };
}

const fn = outer();
fn(); // prints 10
```

- `inner` is a closure.  
- It “remembers” `x` from `outer`, even though `outer` has already finished.  
- As long as `fn` exists, `x` must stay in memory. [developer.mozilla](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Guide/Closures)

So: **closure = function + remembered variables**.

***

## 2. Why closures can keep memory alive

Because a closure holds references to the variables it uses, anything captured:

- Stays in memory as long as the function is reachable.  
- Does **not** get garbage‑collected, even if nothing else points to those variables directly. [humankode](https://www.humankode.com/javascript/javascript-closures-made-easy/)

This is normally good (that’s how private state works), but in long‑lived apps it can accidentally keep:

- Large objects  
- DOM elements (and entire DOM subtrees)  
- Old “page” data  

alive for much longer than intended. [blog.risingstack](https://blog.risingstack.com/explaining-javascript-closure-scope-chain-examples/)

***

## 3. SPA context: why leaks matter more

In a traditional multi‑page app:

- Each page load creates a fresh JS environment.  
- Navigating to a new page wipes the old JS state. [auth0](https://auth0.com/blog/four-types-of-leaks-in-your-javascript-code-and-how-to-get-rid-of-them/)

In an SPA:

- The JS environment often lives as long as the browser tab.  
- Navigation happens via JS (client‑side routing) instead of full reloads.  
- Anything you forget to release can accumulate over time. [dimakuzmich.wordpress](https://dimakuzmich.wordpress.com/2014/04/28/memory-leaks-in-spa-built-with-knockout-js/)

So if closures keep references to things you think are “gone” (old DOM, old stores, old users), memory usage will slowly grow.

***

## 4. Example 1: Closure capturing a DOM tree via a registry

Imagine a modal system where you keep a global list of functions that can open modals.

Global registry:

```js
let modalRegistry = [];
```

Setup:

```js
function setupModal() {
  const modal = document.getElementById("big-modal"); // large DOM subtree
  const data = { big: "object" }; // pretend this is a large object

  function onOpen() {
    // closure uses `modal` and `data`
    modal.classList.add("open");
    console.log(data.big);
  }

  // Save callback so other parts of the app can open this modal
  modalRegistry.push(onOpen);

  return onOpen; // so destroyModal can know which one to remove
}
```

What is in memory now:

- `onOpen` is a closure that captures `modal` and `data`.  
- `modalRegistry` (a global array) stores `onOpen`.  
- Therefore:
  - `onOpen` stays alive.  
  - `modal` and `data` also stay alive, because the closure still references them. [syncfusion](https://www.syncfusion.com/blogs/post/prevent-javascript-memory-leaks-guide)

Later you remove the modal from the DOM:

```js
function destroyModal() {
  const modal = document.getElementById("big-modal");
  modal.remove(); // remove from DOM
}
```

But you do **not** remove `onOpen` from `modalRegistry`.

Result:

- The DOM node is no longer in the document, but `modal` is still referenced inside `onOpen`.  
- `modalRegistry` still references `onOpen`.  
- The garbage collector cannot free that DOM subtree or `data`.  
- If you repeat `setupModal()` and `destroyModal()` many times (e.g., opening/closing different modal instances), you accumulate detached DOM nodes and data in memory. [jscrambler](https://jscrambler.com/blog/the-silent-bug-javascript-memory-leaks)

Safer cleanup:

```js
function destroyModal(onOpen) {
  const modal = document.getElementById("big-modal");
  modal.remove();

  // Remove the closure from the global registry
  modalRegistry = modalRegistry.filter(fn => fn !== onOpen);
}
```

Now:

- After removal from `modalRegistry`, if nothing else references `onOpen`, the closure can be collected.  
- Then `modal` and `data` it captured can be collected too. [syncfusion](https://www.syncfusion.com/blogs/post/prevent-javascript-memory-leaks-guide)

**Key idea:** global arrays/maps of callbacks can keep closures alive, and closures keep their captured DOM/data alive. [gwtproject](https://www.gwtproject.org/articles/dom_events_memory_leaks_and_you.html)

***

## 5. Example 2: Event listener capturing component state

Think of a “component” that mounts some UI and then unmounts when you navigate away.

Bad version:

```js
function mountProfileComponent(user) {
  const root = document.getElementById("profile-root");

  function onClick() {
    console.log("User name:", user.name);
  }

  root.addEventListener("click", onClick);

  return function unmount() {
    // BAD: forgetting to remove the listener
    root.remove(); // remove from DOM only
  };
}
```

What’s happening:

- `onClick` is a closure capturing `user`.  
- `root` has a click listener `onClick`.  
- When you call `unmount`:
  - The DOM node is removed from the document,  
  - But the event system can still have a reference from `root` to `onClick`, and `onClick` still references `user`. [geeksforgeeks](https://www.geeksforgeeks.org/javascript/how-to-handle-memory-leaks-in-javascript/)

If the SPA:

- Mounts this component with `userA`,  
- Unmounts it,  
- Mounts again with `userB`,  
- And so on…

then:

- Old `onClick` closures (for `userA`, `userB`, etc.) can remain in memory.  
- Old `user` objects and their captured data remain as well. [dev](https://dev.to/alisamir/memory-leaks-in-javascript-a-simple-guide-31e8)

Correct unmount:

```js
function mountProfileComponent(user) {
  const root = document.getElementById("profile-root");

  function onClick() {
    console.log("User name:", user.name);
  }

  root.addEventListener("click", onClick);

  return function unmount() {
    root.removeEventListener("click", onClick); // remove closure reference
    root.remove(); // remove node
  };
}
```

Now:

- After `unmount`, nothing references `onClick`.  
- Since `onClick` is dead, `user` and `root` can be garbage‑collected. [dev](https://dev.to/alex_aslam/how-to-avoid-memory-leaks-in-javascript-event-listeners-4hna)

**Key idea:** event targets (`window`, `document`, DOM nodes) can store closures; always remove listeners on unmount.

***

## 6. Example 3: SPA router keeping stale closures (with diagram)

A tiny custom SPA router:

```js
const routes = [];

function registerRoute(path, renderPage) {
  routes.push({ path, renderPage });
}

function navigateTo(path) {
  const route = routes.find(r => r.path === path);
  if (route) route.renderPage();
}
```

A page that registers itself:

```js
function mountDashboard(store) {
  registerRoute("/dashboard", () => {
    const container = document.getElementById("dashboard");
    container.textContent = store.getState().title;
  });
}
```

- The arrow function is a closure capturing `store`.  
- That closure is stored in the global `routes` array under `renderPage`. [auth0](https://auth0.com/blog/four-types-of-leaks-in-your-javascript-code-and-how-to-get-rid-of-them/)

If your SPA:

- Replaces the dashboard multiple times (new `store` instances), and  
- Each time calls `mountDashboard(newStore)`,  
- But **never removes old routes**,

then:

- `routes` keeps many old `renderPage` closures.  
- Each closure keeps its `store` alive.  
- You end up with many old stores and closures sitting in memory. [nolanlawson](https://nolanlawson.com/2020/02/19/fixing-memory-leaks-in-web-applications/)

### 6.1 Diagram: routes and closures

```text
registerRoute("/dashboard", () => {
  // uses `store`
});

We have three main pieces:

  +--------------------------+
  |        routes[]         |   (global array)
  +--------------------------+
            |
            v
  +--------------------------+
  |  {                      |
  |    path: "/dashboard",  |
  |    renderPage:  ──────────────+
  |  }                      |     |
  +--------------------------+     |
                                   |
                                   v
                         +------------------+
                         |  renderPage()    |  (closure function)
                         +------------------+
                                   |
                (closure "backpack"|
                 of remembered vars)
                                   v
                         +------------------+
                         |      store       |
                         +------------------+

- routes[] holds the renderPage function.
- renderPage is a closure that remembers store.
- As long as routes[] keeps this entry, renderPage and store stay in memory.

If we never remove the old entry when we replace the dashboard:

- We add a new { path: "/dashboard", renderPage: (new closure) }.
- The old one is still in routes[], so the old store is also still in memory.
- Repeating this many times leads to many old store instances and closures staying alive.
```

Better approach:

```js
function unregisterRoute(path) {
  const idx = routes.findIndex(r => r.path === path);
  if (idx !== -1) routes.splice(idx, 1);
}

function remountDashboard(newStore) {
  unregisterRoute("/dashboard");
  mountDashboard(newStore);
}
```

Now:

- Old `renderPage` closures are removed from `routes`.  
- They can be garbage‑collected, along with any captured `store`. [nolanlawson](https://nolanlawson.com/2020/02/19/fixing-memory-leaks-in-web-applications/)

***

## 7. Mental model and rules of thumb

**Mental model:**

- Closure = function + a “backpack” of remembered variables.  
- If something still holds a reference to the function, the whole backpack stays in memory. [acte](https://www.acte.in/javascript-closure-explained)

In SPAs, memory leaks often look like:

- A long‑lived object (global array, router, event target) keeps a reference to a closure.  
- The closure’s backpack contains big objects or DOM nodes that you thought were “gone”.  
- Over time, more and more backpacks stay in memory. [jscrambler](https://jscrambler.com/blog/the-silent-bug-javascript-memory-leaks)

**Rules of thumb to avoid closure‑related leaks:**

1. When you store callbacks in global/static structures (arrays, maps, registries):  
   - Provide a way to remove them when no longer needed. [auth0](https://auth0.com/blog/four-types-of-leaks-in-your-javascript-code-and-how-to-get-rid-of-them/)

2. When you add event listeners:  
   - Always remove them on unmount/cleanup. [geeksforgeeks](https://www.geeksforgeeks.org/javascript/how-to-handle-memory-leaks-in-javascript/)

3. Be careful what you capture in closures:  
   - Avoid closing over giant objects or DOM trees when a smaller piece would do. [blog.risingstack](https://blog.risingstack.com/explaining-javascript-closure-scope-chain-examples/)

4. In routers or global services:  
   - Clean up old route handlers, subscriptions, and callbacks when replacing or destroying features. [nolanlawson](https://nolanlawson.com/2020/02/19/fixing-memory-leaks-in-web-applications/)

Closures are essential and powerful; leaks happen only when they are combined with long‑lived references and forgotten cleanup.