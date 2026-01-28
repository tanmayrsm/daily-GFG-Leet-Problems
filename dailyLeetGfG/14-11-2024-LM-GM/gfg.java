class Solution {
    // Non-static method, so you need to call it on an instance of the class
    public void nearlySorted(int[] arr, int k) {
        // code
        int idx = 0, n = arr.length;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i=0; i<= n+k; i++) {
            if (i > k) arr[i-k-1] = pq.poll();
            if (i < n) pq.offer(arr[i]);
        }
        
    }
}