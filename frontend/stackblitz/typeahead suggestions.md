# Typeahead Suggestions Notes

StackBlitz reference:
https://stackblitz.com/edit/react-qztaevwl?file=src%2FApp.js,src%2Findex.js,src%2Fservice%2FgetSuggestionFromAPI.js,src%2Fservice%2FgetFilteredSuggestions.js&file=src%2Fservice%2FgetDebounced.js,src%2Fservice%2FgetSuggestionFromAPI.js,src%2Fservice%2FgetFilteredSuggestions.js

## 1. Mixing `promise.then(...)` with `await get()`

- You created a **Promise value**, then later tried to call it like a function.

Bad:

```js
const apiPromise = fetch(url).then(r => r.json());
// ...
const data = await apiPromise(); // ❌ apiPromise is not a function
```

Correct options:

- Treat it as a Promise value:

```js
const apiPromise = fetch(url).then(r => r.json());
const data = await apiPromise; // ✅ no ()
```

- Or make it a function that returns a Promise:

```js
const getData = async () => {
  const res = await fetch(url);
  return res.json();
};

const data = await getData(); // ✅ call the function
```

## 2. Import / export mismatch (default vs named)

- You exported **default** but imported as **named**, or vice-versa.

Examples:

```js
// getDebounced.js
export default getDebouncedRes;
```

Then:

```js
import getDebouncedRes from './service/getDebounced';     // ✅
import { getDebouncedRes } from './service/getDebounced'; // ❌ (named)
```

If you use a named export:

```js
export const getDebouncedRes = ...;
```

Then:

```js
import { getDebouncedRes } from './service/getDebounced'; // ✅
```

Rule:

- `export default X` ↔ `import X from '...'`
- `export const X` ↔ `import { X } from '...'`

## 3. LocalStorage and `JSON.parse` / `JSON.stringify`

- `localStorage` always stores **strings**, so saving arrays/objects directly and reading them back returns a string, not the original structure.
- Returning that string and later doing `res.map(...)` or `res.filter(...)` causes "`res.map is not a function`".

Wrong:

```js
localStorage.setItem(key, suggestions); // ❌ suggestions is an array
const cached = localStorage.getItem(key); // string
return cached; // later: cached.map(...) ❌
```

Correct:

```js
// store
localStorage.setItem(key, JSON.stringify(suggestions));

// read
const cachedStr = localStorage.getItem(key);
if (cachedStr != null) {
  const cached = JSON.parse(cachedStr); // back to array/object
  return cached;
}
```
