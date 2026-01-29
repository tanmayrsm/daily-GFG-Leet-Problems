class Solution {
    public int minTime(int n, int[][] edges) {
        boolean[] vis = new boolean[n];
        int ans = Integer.MAX_VALUE;
        List<List<int[]>> adj = new ArrayList<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>((int[] a, int[] b) -> Integer.compare(a[1], b[1]));
        for (int i = 0; i < n; i++)
            adj.add(new ArrayList<>());
        // 0 : [1,50,55], [2,34,36] type adj list
        for (int[] edge : edges) {
            int src = edge[0], dest = edge[1], fromTime = edge[2], toTime = edge[3];
            adj.get(src).add(new int[] {dest, fromTime, toTime});
        }
        pq.offer(new int[] {0, 0});  // {src, currTime}
        while (!pq.isEmpty()) {
            int[] currList = pq.poll();
            int src = currList[0], currTime = currList[1];
            if (src == n - 1) {
                return currTime;
            }
            if (!vis[src]) {
                vis[src] = true;
                for (int[] neigh : adj.get(src)) {
                    int next = neigh[0], timeReq = neigh[1], timeEnd = neigh[2];
                    if (!vis[next]) {
                        // vis[next] = true;
                        if (timeReq > currTime) {
                            pq.offer(new int[] {next, timeReq + 1});
                        } else if (timeReq <= currTime && currTime <= timeEnd) pq.offer(new int[] {next, currTime + 1});
                    }
                }
            }
        }
        return -1;
    }
}