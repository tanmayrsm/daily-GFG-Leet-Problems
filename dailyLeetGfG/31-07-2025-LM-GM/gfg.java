class Solution {
    public int powerfulInteger(int[][] intervals, int k) {
        // code here
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[0] - a[0]);

        // Push all intervals into the priority queue as [end, start, count]
        for (int[] x : intervals) {
            pq.offer(new int[]{x[1], x[0], 1});
        }

        // If only 1 overlap is required, return the latest end
        if (k == 1) return pq.peek()[0];

        while (pq.size() > 1) {
            int[] it1 = pq.poll();
            int[] it2 = pq.peek();

            // Disjoint: no overlap
            if (it2[0] < it1[1]) continue;

            // Overlapping and count satisfies condition
            if (it1[2] + it2[2] >= k) return it2[0];

            pq.poll(); // Remove it2

            int minStart = Math.min(it1[1], it2[1]);
            int maxStart = Math.max(it1[1], it2[1]);

            // Push merged overlapping part
            pq.offer(new int[]{it2[0], maxStart, it1[2] + it2[2]});

            // Push leftover non-overlapping part if exists
            if (minStart != maxStart) {
                int f = (it1[1] == minStart) ? it1[2] : it2[2];
                pq.offer(new int[]{maxStart - 1, minStart, f});
            }
        }

        return -1;
    }
}