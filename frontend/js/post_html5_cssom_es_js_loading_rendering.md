# Post-HTML5 Notes: CSSOM, ES Versions, JS Loading, Browser Painting

Here is a compact notes summary for post-HTML5 topics: CSSOM, ES versions, JS loading, and browser painting.

## CSSOM (CSS Object Model)

- Browser parses all CSS (inline, `<style>`, external files) into a CSSOM tree: a structured representation of rules and computed styles.
- Together, DOM + CSSOM form the Render Tree, which is then used for layout and painting.

## ES5 Basics (What It Added)

- Core helpers on `Object`: `Object.create`, `Object.defineProperty`, `Object.freeze`, `Object.seal`, `Object.preventExtensions`, `Object.keys`, etc.
- Array helpers: `forEach`, `map`, `filter`, `reduce`, `some`, `every`, `indexOf`, `lastIndexOf`.
- `JSON.parse`, `JSON.stringify`, `"use strict"`, and `Function.prototype.bind`.
- ES4 was planned but cancelled; standards moved ES3 -> ES5 -> ES2015 (ES6).

## ES5 vs ES6 vs ES7 (Super Short)

- ES5 (2009): classic JS features, only `var`, no classes/modules/arrow functions, plus helpers above.
- ES6 / ES2015: big upgrade with `let`/`const`, arrow functions, template literals, destructuring, default/rest/spread, `class`, `import/export`, `Map`, `Set`, generators, `for...of`, Promises.
- ES7 / ES2016: small release with `a ** b` and `Array.prototype.includes`.
- Later versions added features like `async/await`, `Object.entries`, optional chaining, and more.

## `"use strict"`

- `"use strict";` enables strict mode, making JS safer and less error-prone.
- It blocks implicit globals, converts some silent failures into errors, and changes some `this` behavior.
- ES modules (`<script type="module">`) are strict by default.

## JS Download and Execution: Normal vs Async vs Defer

- Normal `<script>`:
  - HTML parse runs.
  - Parser hits script.
  - Parsing pauses, script downloads and executes.
  - Parsing resumes.

- `<script async>`:
  - HTML parsing and script download happen in parallel.
  - When ready, parsing pauses and script runs immediately.
  - Parsing then resumes.
  - Execution order across multiple async scripts is not guaranteed.

- `<script defer>`:
  - HTML parsing and script download happen in parallel.
  - Deferred scripts run after HTML parsing completes.
  - Multiple deferred scripts run in document order.
  - Runs before `DOMContentLoaded`.

## Browser Rendering / Painting Pipeline (Simplified)

1. HTML -> DOM
   - Browser parses HTML into DOM nodes.
2. CSS -> CSSOM
   - Browser parses CSS into the CSSOM tree.
3. DOM + CSSOM -> Render Tree
   - Includes visible elements with final computed styles.
4. Layout (reflow)
   - Browser computes position and size of boxes.
5. Paint
   - Browser draws pixels: text, colors, borders, images, shadows.
6. Further changes
   - JS/CSS updates may trigger style -> layout -> paint again.
   - Excessive layout/paint work hurts performance.

## Next Step

Turn this into a one-page mental diagram / cheat sheet for quick reference while coding.
