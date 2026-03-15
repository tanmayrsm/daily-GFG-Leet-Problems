import { useRef, useCallback } from "react";

function useDebouncedAsync(func, delay) {
  const timeoutRef = useRef();

  const debounced = useCallback(
    (...args) => {
      if (timeoutRef.current) {
        clearTimeout(timeoutRef.current);
      }

      return new Promise((resolve, reject) => {
        timeoutRef.current = setTimeout(async () => {
          try {
            const result = await func(...args);
            resolve(result);
          } catch (e) {
            reject(e);
          }
        }, delay);
      });
    },
    [func, delay]
  );

  return debounced;
}

export default useDebouncedAsync;
