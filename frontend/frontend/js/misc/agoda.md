# Agoda Frontend Platform – Brief Prep Notes

## 1. How the browser renders HTML to pixels

- Parse HTML into a DOM tree and CSS into a CSSOM tree.  
- Combine DOM + CSSOM into a render tree of visible nodes with computed styles.  
- Layout (reflow): compute size and position of each node.  
- Paint: draw colors, text, borders, images to layers.  
- Compositing: GPU combines layers into the final image on screen.

---

## 2. Reflow vs repaint and how to minimize them

### Reflow (layout)
- Triggered by layout changes: DOM add/remove, size, fonts, `display`, `position`, margins, etc.  
- Can cascade and recalc layout for large parts of the page.

### Repaint (paint only)
- Triggered by visual changes that don’t affect layout: `color`, `background`, `box-shadow`, `opacity`, etc.  
- Cheaper than reflow but still work.

### How to minimize
- Batch DOM reads, then DOM writes (avoid read‑write‑read thrashing).  
- Avoid layout‑thrashing APIs in loops; cache measurements.  
- Prefer `transform`/`opacity` for animations instead of `top/left` or size.  
- Set fixed dimensions for images/containers to prevent late layout shifts.

---

## 3. Slow first load – hotel search page

### Investigate
- Use performance tooling to inspect:
  - TTFB and network waterfall.  
  - Bundle sizes, blocking JS/CSS, long tasks.  
  - LCP element and other Web Vitals.

### Optimize
- Reduce JS: code‑split, tree‑shake, remove unused libs, defer non‑critical scripts.  
- Optimize CSS: critical CSS for above‑the‑fold, lazy‑load the rest.  
- Reduce number/latency of initial API calls; aggregate or parallelize.  
- Consider SSR/streaming for first view; show shell quickly with skeletons.  
- Break long tasks, lazy‑load below‑the‑fold components, compress/optimize images.  
- Re‑measure and monitor in production.

---

## 4. React code‑splitting and lazy loading

### What they are
- Code‑splitting: split bundle into chunks loaded when needed (per route/feature).  
- Lazy loading: load those chunks on demand (e.g., `React.lazy`, dynamic `import()`).

### How to use
- Route‑level splitting for major pages (Search, Details, Checkout).  
- Component‑level splitting for heavy/rare components (charts, maps, rich editors).  
- Preload/prefetch chunks for likely next routes to hide latency.

### Where it can hurt UX
- Too many tiny chunks → many requests, frequent “loading…” states.  
- Lazy‑loading critical UI → slower first interaction, janky transitions.  
- Poor fallback UI → blank areas, layout jumps when chunk loads.  
- Over‑splitting common flows → navigation feels sluggish.

---

## 5. Core Web Vitals (LCP, FID/INP, CLS) in a large SPA

### Measuring
- Use field (real‑user) data via analytics/RUM for LCP, FID/INP, CLS.  
- Use lab tools for debugging, not as the only source.  
- In SPAs, measure per route; CLS can accumulate across navigation.

### Improving LCP
- Prioritize main content: preload hero image / key text.  
- Reduce render‑blocking JS/CSS; inline critical CSS.  
- Use CDN and fast APIs; consider SSR for main content.

### Improving FID/INP
- Trim JS bundles; avoid heavy libraries on initial route.  
- Break long tasks into smaller ones; defer non‑critical work.  
- Avoid heavy synchronous work in event handlers.

### Improving CLS
- Always reserve space (width/height) for images, ads, embeds.  
- Avoid inserting content above existing content without space.  
- Avoid layout‑affecting animations; use `transform` instead of `top/height`.  
- Load fonts in a way that avoids big text jumps.

---

## 6. Highly reusable table component (sorting, pagination, virtualization)

### Design goals
- Reusable across many teams and use cases.  
- Supports client/server sorting and pagination.  
- Efficient with large data sets via virtualization.  
- Accessible and themable.

### Core design
- Headless table: component manages state/logic; consumers render UI (render props/slots).  
- Config‑driven columns: `key`, `title`, `sortable`, `renderCell`.  
- Props for:
  - `data`, `page`, `pageSize`, `sortBy`, `sortDirection`.  
  - Callbacks: `onSortChange`, `onPageChange`, `onRowClick`.

### Sorting & pagination
- Client‑side: table sorts and slices data internally.  
- Server‑side: table emits sort/pagination changes; parent fetches data and passes back rows.

### Virtualization
- Use a virtualization strategy so only visible rows render.  
- Compute visible range based on scroll offset and row height.  
- Keep header fixed; support sticky columns when needed.

### Reuse & platform aspects
- Separate logic from styling; integrate with design system tokens.  
- Provide sensible defaults (loading, empty state, basic styles).  
- Document patterns and anti‑patterns; provide Storybook examples.

---

## 7. Deep JavaScript – key points to review

- Event loop: call stack, task queue, microtasks; order of execution.  
- Promises: chaining, error handling, `Promise.all/*`, `async/await`.  
- Closures and `this` binding; `call` / `apply` / `bind`.  
- Debounce vs throttle and when to use each.  
- Modules and bundling basics (ESM, tree‑shaking).

---

## 8. Frontend architecture at scale

- Module boundaries: separate core platform modules (design system, data layer, routing).  
- Microfrontends or single‑SPA with clear ownership per domain.  
- Shared component library and design tokens.  
- API integration layer with typed clients and caching.  
- Feature flags, A/B testing hooks, observability baked into the platform.

---

## 9. Frontend‑centric system design (examples)

### Booking flow (search → details → checkout)
- Clear route structure; share common layout (header, auth).  
- Central data layer for user, itinerary, prices; cache across steps.  
- Optimistic UI where safe; robust error and retry strategies.  
- Handle partial failures gracefully (e.g., price changed).

### Real‑time dashboard
- Choose transport (WebSockets, SSE, or polling).  
- Normalize incoming events; update state incrementally.  
- Virtualize large lists; aggregate data where possible.  
- Back‑pressure strategies (throttling updates, sampling).

### Configuration platform (non‑tech users)
- Config‑driven UI: schema that describes components, layout, rules.  
- Strong validation and preview before rollout.  
- Feature flags and safe rollback.  
- Audit logs for changes.

---

## 10. Senior ownership / behavioral topics

Prepare 2–3 stories (STAR: Situation, Task, Action, Result) about:

- Improving a shared frontend platform or design system.  
- Leading a migration (e.g., to React, TypeScript, new router/state lib).  
- Driving performance improvements with measurable results.  
- Handling conflict or trade‑offs between speed vs quality, product vs platform.

