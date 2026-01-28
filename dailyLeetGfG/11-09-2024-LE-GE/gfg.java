
//Back-end complete function Template for Java
class Solution {
    // Function to return the minimum cost of connecting the ropes.
    public long minCost(long[] arr) {
        // code here
        long ans = 0;
        PriorityQueue<Long> pq = new PriorityQueue<>();
        for(long a : arr)
            pq.offer(a);
        while(!pq.isEmpty()) {
            long n1 = pq.poll();
            if (!pq.isEmpty()) {
                long no = n1 + pq.poll();
                pq.offer(no);
                ans += no;
            }
        }
        return ans;
    }
}