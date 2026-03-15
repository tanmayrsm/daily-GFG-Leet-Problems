import { useEffect, useRef, useState } from "react";

/**
 * A minimal React hook for infinite scroll with cursor-based pagination.
 * Uses IntersectionObserver to detect when the sentinel element comes into view.
 *
 * @param {Function} fetchPage - Async function that fetches a page of items.
 *   - Signature: (cursor: string | null) => Promise<{ items: T[], nextCursor: string | null }>
 *   - cursor: null for first page, then opaque cursor string from previous response
 *   - Returns an object with items array and nextCursor (null if no more pages)
 *
 * @returns {Object} - { items, loading, loaderRef }
 *   - items: Array of all items fetched so far
 *   - loading: Boolean indicating if a page is currently being loaded
 *   - loaderRef: Ref to attach to a sentinel div (will trigger load when visible)
 *
 * @example
 * function PostsList() {
 *   const { items, loading, loaderRef } = useInfiniteCursor(fetchPosts);
 *
 *   return (
 *     <>
 *       {items.map(post => <div key={post.id}>{post.title}</div>)}
 *       {loading && <p>Loading...</p>}
 *       <div ref={loaderRef} style={{ height: 1 }} />
 *     </>
 *   );
 * }
 */
export function useInfiniteCursor(fetchPage) {
  const [items, setItems] = useState([]);
  const [cursor, setCursor] = useState(null);
  const [loading, setLoading] = useState(false);
  const loaderRef = useRef(null);
  const [done, setDone] = useState(false); // internal flag, not exposed

  useEffect(() => {
    if (done) return;

    const observer = new IntersectionObserver(async (entries) => {
      const first = entries[0];
      if (!first.isIntersecting || loading || done) return;

      setLoading(true);
      try {
        const res = await fetchPage(cursor);
        setItems((prev) => [...prev, ...res.items]);
        setCursor(res.nextCursor);
        if (!res.nextCursor) {
          setDone(true);
        }
      } catch (error) {
        console.error("Error fetching page:", error);
      } finally {
        setLoading(false);
      }
    });

    const el = loaderRef.current;
    if (el) observer.observe(el);

    return () => observer.disconnect();
  }, [cursor, loading, done, fetchPage]);

  return { items, loading, loaderRef };
}

export default useInfiniteCursor;
