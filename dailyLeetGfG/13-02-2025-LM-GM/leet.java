class Solution {
    public int minOperations(int[] nums, int k) {
        PriorityQueue<Long> pq = new PriorityQueue<>();
        int steps = 0;
        for(int num : nums) 
            pq.offer(Long.valueOf(num));
        while(!pq.isEmpty() && pq.peek() < k) {
            long first = pq.poll();
            if (pq.isEmpty())
                break;
            long second = pq.poll();
            long res = Math.min(first, second) * 2 + Math.max(first, second);
            pq.offer(res);
            steps++;
        }
        return steps;
    }
}