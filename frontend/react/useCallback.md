Think of `useCallback` as: “keep this **function** the same between renders unless these values change.” It’s about keeping the function **reference** stable so children/effects don’t re-run unnecessarily. [react](https://react.dev/reference/react/useCallback)

### Tiny example (no child, just the idea)

```jsx
function Counter() {
  const [count, setCount] = useState(0);

  const handleClick = useCallback(() => {
    setCount(c => c + 1);
  }, []);

  return <button onClick={handleClick}>Count: {count}</button>;
}
```

Here, `handleClick` is created once and then reused on every render, because its dependency array is `[]`. [geeksforgeeks](https://www.geeksforgeeks.org/reactjs/react-js-usecallback-hook/)

***

### Small example where it actually matters

You have a parent and a child. The child is wrapped in `React.memo`, so it only re-renders if its props **change by reference**.

```jsx
const Child = React.memo(function Child({ onClick }) {
  console.log("Child render");
  return <button onClick={onClick}>Child button</button>;
});

function Parent() {
  const [count, setCount] = useState(0);

  // BAD: new function every render
  // const handleClick = () => setCount(c => c + 1);

  // GOOD: same function reference until deps change
  const handleClick = useCallback(() => {
    setCount(c => c + 1);
  }, []);

  console.log("Parent render");
  return (
    <>
      <p>Count: {count}</p>
      <Child onClick={handleClick} />
    </>
  );
}
```

- Without `useCallback`, `handleClick` is a **new function** each render → `Child` sees a new `onClick` prop and re-renders every time.  
- With `useCallback`, `handleClick` keeps the **same identity** between renders → `Child` doesn’t re-render unless something that matters changes (like other props). [blog.logrocket](https://blog.logrocket.com/react-usememo-vs-usecallback/)

If you paste this into a sandbox and toggle between the commented “BAD” and “GOOD” versions, you’ll see `Child render` spam vs. only when needed.