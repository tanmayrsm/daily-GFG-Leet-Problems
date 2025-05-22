class Solution
{
    public int maxRemoval(int[] nums, int[][] queries)
    {
        int n = nums.length;

        // Step 2: Initialize Heaps
        PriorityQueue<Integer   > usedQuery = new PriorityQueue<>(); // min-heap
        PriorityQueue<Integer> availableQuery = new PriorityQueue<>(Collections.reverseOrder()); // max-heap

        // Step 1: Sort the queries
        Arrays.sort(queries, Comparator.comparingInt(a -> a[0]));

        int queryPos = 0;
        int appliedCount = 0;

        // Step 3: Traverse nums
        for (int i = 0; i < n; i++)
        {
            // Step 3a: Add queries starting at i
            while (queryPos < queries.length && queries[queryPos][0] == i)
            {
                availableQuery.offer(queries[queryPos][1]);
                queryPos++;
            }

            // Step 3b: Adjust value at i based on active queries
            nums[i] -= usedQuery.size();

            // Step 3c: Apply more queries if needed
            while (nums[i] > 0 && !availableQuery.isEmpty() && availableQuery.peek() >= i)
            {
                usedQuery.offer(availableQuery.poll());
                nums[i]--;
                appliedCount++;
            }

            // Step 3d: If we can't zero nums[i]
            if (nums[i] > 0)
            {
                return -1;
            }

            // Step 3e: Remove expired queries
            while (!usedQuery.isEmpty() && usedQuery.peek() == i)
            {
                usedQuery.poll();
            }
        }

        // Step 4: Return result
        return queries.length - appliedCount;
    }
}