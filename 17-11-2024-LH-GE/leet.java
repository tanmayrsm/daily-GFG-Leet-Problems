class Solution {
    public int shortestSubarray(int[] nums, int k) {    // reff
        
        int nLen = nums.length;
        int shortest = nLen + 1;
        
		// Building a prefix/cummulative sum of all elements
        long[] prefixSum = new long[nLen + 1];
        for(int i=0; i < nLen; i++)
            prefixSum[i+1] = prefixSum[i] + nums[i];
        
		// Initialize the Deque for storing the starting indices
        Deque<Integer> startIdxs = new ArrayDeque<>();
        
        for(int i=0; i < nLen + 1; i++){
            // Finding the smallest window whose sum >= k
            while(!startIdxs.isEmpty() && prefixSum[i] - prefixSum[startIdxs.peek()] >= k)
                shortest = Math.min(shortest, i - startIdxs.poll());
            
            // Keeping the startIdxs deque in an order of increasing sequence
            while(!startIdxs.isEmpty() && prefixSum[i] <= prefixSum[startIdxs.peekLast()])
                startIdxs.pollLast();
            
            // Add the current index to the startIdxs queue
            startIdxs.add(i);
        }
        
        return shortest <= nLen ? shortest : -1;
    }
}