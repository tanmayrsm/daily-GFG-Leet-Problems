// 1) Expensive calculation derived from state
// You have a large list and a heavy filter/sort; every keystroke re-renders and runs that heavy work.

function UserList({ users, search }) {
  const filteredUsers = React.useMemo(() => {
    // assume this is heavy: complex filter + sort
    return users
      .filter(u => u.name.toLowerCase().includes(search.toLowerCase()))
      .sort((a, b) => a.name.localeCompare(b.name));
  }, [users, search]);

  return (
    <ul>
      {filteredUsers.map(u => <li key={u.id}>{u.name}</li>)}
    </ul>
  );
}
