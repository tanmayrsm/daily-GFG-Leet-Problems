class Solution {
    public static int minCost(int[] arr) {
        // code here
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int ans = 0;
        // if (arr.length == 1) return 0;
        for (int x : arr)   pq.offer(x);
        while(!pq.isEmpty()) {
            int f = pq.poll();
            if(pq.isEmpty())    return ans;
            int s = pq.poll();
            ans += f + s;
            pq.offer(f + s);
        }
        return 0;
    }
}