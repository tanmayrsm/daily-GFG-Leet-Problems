# Senior Frontend/Platform Signals – Concepts & Use Cases

## 1. ETag & HTTP caching

- **What it is**: An HTTP mechanism where the server tags a response (e.g., JSON config, HTML) with a version/hash, and the client revalidates using that tag instead of always refetching the full body.  
- **Use case – Config‑driven pages**: Serve CMS JSON config via CDN with ETag/Last‑Modified so most user requests get a cached or `304 Not Modified` response, and only fetch full JSON when content actually changes.  
- **Use case – SSR/SEO**: Cache server‑rendered HTML for popular routes, revalidate with ETags to keep content fresh while avoiding full renders on every request.  

---

## 2. Bundle size budgets / restrictions

- **What it is**: A hard or soft limit on JS/CSS bundle sizes, usually enforced in CI (e.g., max 250 KB gzipped for critical path).  
- **Use case – Microfrontends**: Each MFE has bundle budgets; builds fail or warn when budgets are exceeded, preventing slow regressions from creeping into production.  
- **Use case – Performance platform**: A central dashboard tracks bundle sizes across teams over time, highlighting when a new dependency or feature blows up the bundle.  

---

## 3. Semver (semantic versioning)

- **What it is**: Versioning scheme `MAJOR.MINOR.PATCH`, where MAJOR = breaking change, MINOR = new backward‑compatible feature, PATCH = bugfix.  
- **Use case – Shared component library**: Publish the design system to a private registry using semver; teams can safely upgrade PATCH/MINOR versions and plan for MAJOR migrations with deprecation notes and guides.  
- **Use case – Shared SDKs/API clients**: Backend‑generated client SDKs (REST/GraphQL) follow semver so frontend teams know when API changes might break them.  

---

## 4. Shared components & shell (“platform layer”)

- **What it is**: A shared frontend layer exposing common UI (header, footer, nav), state (auth, feature flags), and utilities that all MFEs consume.  
- **Use case – Microfrontends**: A shell app or shared package provides layout, design system, and hooks like `useAuth`, `useFeatureFlag`, reducing duplication and making cross‑cutting changes (like a new login flow) consistent.  
- **Use case – Experiments & flags**: The shared layer is the only place where flags/experiments are evaluated; MFEs just ask “is this flag on?” so behavior stays consistent.  

---

## 5. Event bus / event queue (frontend)

- **What it is**: A lightweight pub/sub mechanism in the browser (custom event emitter, BroadcastChannel, etc.) that lets parts of the app communicate without tight coupling.  
- **Use case – Cross‑MFE communication**: One MFE publishes `auth:logged-in`, others subscribe and react (refresh data, update UI) without direct imports between MFEs.  
- **Use case – Global state changes**: Config, feature flags, or locale changes are broadcast via events so all interested components/MFEs update in sync.  

---

## 6. Web Workers

- **What it is**: Background threads in the browser where you can run JS without blocking the main UI thread.  
- **Use case – High‑traffic pages (search, listings)**: Offload heavy computations (sorting, scoring, filtering large result sets) to a worker so typing, scrolling, and clicking stay smooth.  
- **Use case – Data processing / encoding**: Run CPU‑intensive tasks (compression, image processing, encryption, complex diffing) in workers instead of freezing the UI.  

---

## 7. Shared dependency strategy (React, UI libs, etc.)

- **What it is**: A deliberate plan for which libraries and versions are shared across MFEs vs bundled per app.  
- **Use case – Bundle bloat**: Ensure all MFEs use a single version of React and key libraries (via module federation/import maps or shared npm deps) to avoid shipping multiple copies.  
- **Use case – Stability**: Central ownership for upgrading core dependencies; MFEs adopt new versions in a controlled way instead of everyone upgrading randomly.  

---

## 8. Platform‑level logging, metrics & dashboards

- **What it is**: Common observability standards: structured logs, metrics (latency, errors, bundle size), and tracing across frontends.  
- **Use case – Bundle/perf regressions**: Track Core Web Vitals, JS size, and errors per route/MFE to quickly see which change hurt performance.  
- **Use case – Feature rollouts**: Combine metrics with feature flags to see impact of new features per cohort/region and roll back fast if needed.  
