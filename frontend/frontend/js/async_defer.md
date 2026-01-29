# Script loading: `script`, `async`, `defer`

![Script Loading Diagram](img/defer-javascript-en.png)

## Summary

- **`<script>`**: Blocks HTML parsing while it loads and runs.
- **`<script async>`**: Loads in parallel and runs as soon as it’s ready (order not guaranteed).
- **`<script defer>`**: Loads in parallel and runs after HTML parsing is finished, in order.

---

## Normal `<script src="...">`

- HTML parsing stops when the browser hits the script.
- Browser downloads the script.
- Browser executes the script.
- Then parsing continues.
- Blocks page rendering during load + execution.

Flow:

- `Parse → [download + execute script] → Parse`

---

## `<script async src="...">`

- Script starts downloading while HTML is still parsing.
- When download finishes, parsing is paused.
- Script executes immediately.
- Parsing then resumes.
- Execution order is not guaranteed if there are multiple async scripts.
- Good for independent scripts (e.g., analytics, trackers).

Flow:

- `Parse + download (parallel) → when ready: [execute script] → Parse`

---

## `<script defer src="...">`

- Script starts downloading while HTML is still parsing.
- Script does **not** execute immediately.
- After HTML parsing is complete, all deferred scripts run in document order.
- `DOMContentLoaded` fires after deferred scripts finish.
- Good for main app scripts that need the full DOM.

Flow:

- `Parse + download (parallel) → finish parsing → [execute deferred scripts in order] → DOMContentLoaded`
