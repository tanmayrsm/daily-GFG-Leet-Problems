class Solution {
    public int kthSmallest(int[][] mat, int k) {
        // code here
        PriorityQueue<Integer> pq = new PriorityQueue<>((Integer a, Integer b) -> Integer.compare(b, a));
        int n = mat.length, currSize = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (currSize < k)   {
                    pq.offer(mat[i][j]);
                    currSize++;
                } else if (pq.peek() > mat[i][j]) {
                    pq.poll();
                    pq.offer(mat[i][j]);
                }
            }
        }
        int res = pq.poll();
        pq.clear();
        return res;
    }
}
