import { useEffect } from "react";
import useDebounce from "./debounce";
import useFetch from "./useFetch";
import useLocalStorage from "./useLocalStorage";

function useSearchSuggestions(query) {
  const debouncedQuery = useDebounce(query, 500);
  const [results, setResults] = useLocalStorage(`search:${debouncedQuery}`, []);
  const { data, loading, error } = useFetch(
    debouncedQuery
      ? `https://jsonplaceholder.typicode.com/posts?q=${debouncedQuery}`
      : null
  );

  useEffect(() => {
    if (data) setResults(data);
  }, [data, setResults]);

  return { results, loading, error };
}

export default useSearchSuggestions;
