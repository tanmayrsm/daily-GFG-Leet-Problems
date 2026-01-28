class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int[] flight : flights) {
            adj.get(flight[0]).add(new int[] {flight[1], flight[2]});
        }
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {src, 0});
        int[] minCost = new int[n];
        Arrays.fill(minCost, Integer.MAX_VALUE);
        int stops = 0;
        while (!q.isEmpty() && stops <= k) {
            int size = q.size();
            while (size-- > 0) {
                int[] curr = q.poll();
                for (int[] neighbour : adj.get(curr[0])) {
                    int price = neighbour[1], neighbourNode = neighbour[0];
                    if (price + curr[1] >= minCost[neighbourNode]) continue;
                    minCost[neighbourNode] = price + curr[1];
                    q.offer(new int[] {neighbourNode, minCost[neighbourNode]});
                }
            }
            stops++;
        }
        return minCost[dst] == Integer.MAX_VALUE ? -1 : minCost[dst];
    }

    // private void dfs(int src, int dest, int k, int currK, int currCost, List<List<Integer>> adj, boolean[] vis, int[][] costArr) {
    //     if(currK > k)
    //         return;
    //     minCost[src] = Math.min(minCost[src], currCost);
    //     if(src == dest) {
    //         return;
    //     }
    //     // System.out.println("FOr src dest ::" + src + " :: " + dest + " :: " + minCost[src]);
    //     for(int neighbour : adj.get(src)) {
    //         if(!vis[neighbour] && (currCost + costArr[src][neighbour] < minCost[neighbour] || minCost[dest] == Integer.MAX_VALUE)) {
    //             vis[neighbour] = true;
    //             // System.out.println("new Src and dest :: " + neighbour);
    //             dfs(neighbour, dest, k, neighbour != dest ? currK + 1: currK, currCost + costArr[src][neighbour], adj, vis, costArr);
    //             vis[neighbour] = false;
    //         }
    //     }
    // }
}