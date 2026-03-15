## 1. Custom hooks: what and why

- A custom hook is a function that:
- Starts with `use` (example: `useFetch`, `useDebounce`).
- Uses other hooks inside (`useState`, `useEffect`, `useRef`, `useCallback`, `useMemo`).
- Encapsulates reusable logic and returns a clean API (values + actions).

### Skeleton for any custom hook

```js
import {
  useState,
  useEffect,
  useRef,
  useCallback,
  useMemo,
} from "react";

function useSomething(params) {
  // 1. State / refs for internal data
  // const [state, setState] = useState(initial);
  // const ref = useRef(initialValue);

  // 2. Side effects (API calls, listeners, timers)
  // useEffect(() => { ... }, [deps]);

  // 3. Memoized callbacks or derived values
  // const fn = useCallback(() => { ... }, [deps]);
  // const value = useMemo(() => compute(state), [state]);

  // 4. Return what the component needs
  return {
    // or return [value, setValue]
  };
}

export default useSomething;
```

Use it like:

```js
const { data, loading } = useSomething(params);
```

---

## 2. Available Custom Hooks in This Directory

### [useInfiniteCursor](useInfiniteCursor.js)
Minimal hook for infinite scroll with **cursor-based pagination** and `IntersectionObserver`.

**API:**
```js
const { items, loading, loaderRef } = useInfiniteCursor(fetchPage);
```

**Features:**
- Only exposes: `items`, `loading`, `loaderRef` (clean API)
- Internal state: `cursor` and `done` (not exposed to consumer)
- Uses `IntersectionObserver` to detect when sentinel element becomes visible
- Automatically stops when `nextCursor` is null

**Backend requirement:**
```ts
fetchPage(cursor) => Promise<{ items: T[], nextCursor: string | null }>
```

**Example:**
```tsx
async function fetchPosts(cursor) {
  const params = new URLSearchParams();
  if (cursor) params.set("cursor", cursor);
  const res = await fetch("/api/posts?" + params);
  return res.json();
}

function PostsList() {
  const { items, loading, loaderRef } = useInfiniteCursor(fetchPosts);

  return (
    <>
      {items.map(post => <div key={post.id}>{post.title}</div>)}
      {loading && <p>Loading...</p>}
      <div ref={loaderRef} style={{ height: 1 }} />
    </>
  );
}
```

---

### [useFetch](useFetch.js)
Hook for fetching data with loading and error states.

---

### [useDebouncedAsync](useDebouncedAsync.js)
Debounce async operations (e.g., search input that calls API).

---

### [useLocalStorage](useLocalStorage.js)
Sync state with browser localStorage with automatic persistence.

---

### [useSearchSuggestions](useSearchSuggestions.js)
Hook for typeahead/autocomplete search with debounce.

---

### [debounce.js](debounce.js)
Utility for debouncing functions (used by other hooks).
