import { useMemo } from "react";

// useMemo - memoize computed values
// Purpose: avoid recomputing expensive derived values
// (sorting, filtering, heavy calculations) unless dependencies change.
function UserList({ items }) {
  const sorted = useMemo(() => {
    return items.slice().sort((a, b) => a.value - b.value);
  }, [items]);

  return (
    <ul>
      {sorted.map((item) => (
        <li key={item.id}>{item.value}</li>
      ))}
    </ul>
  );
}

export default UserList;
