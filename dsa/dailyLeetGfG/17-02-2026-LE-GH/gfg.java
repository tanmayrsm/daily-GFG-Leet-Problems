
class Solution {
    public static int overlapInt(int[][] arr) {
        // code here
        int ans = 1;
        Arrays.sort(arr, (int[] a, int[] b) -> {
            if(a[0] == b[0])    return Integer.compare(a[1], b[1]);
            return Integer.compare(a[0], b[0]);
        });
        PriorityQueue<int[]> pq = new PriorityQueue<>((int[] a, int[] b) -> {
            if(a[1] == b[1])    return Integer.compare(a[0], b[0]);
            return Integer.compare(a[1], b[1]);
        });
        pq.offer(arr[0]);
        for (int i = 1; i < arr.length; i++) {
            int[] curr = arr[i];
            while(!pq.isEmpty() && pq.peek()[1] < curr[0])
                pq.poll();
            ans = Math.max(ans, pq.size() + 1);
            pq.offer(curr);
        }
        return ans;
    }
}
