import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    
    public int[] shortestPath(int[][] edges,int n,int m ,int src) {
        // Code here
        // typical dijkstra algo
        int[] cost = new int[n];
        Arrays.fill(cost, Integer.MAX_VALUE);
        cost[src] = 0;
        Queue<Integer> q = new LinkedList<>();
        q.offer(src);

        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++)
            adj.add(new ArrayList<>());
        for (int[] edge : edges) {
            int source = edge[0], dest = edge[1];
            adj.get(source).add(dest);
            adj.get(dest).add(source);
        }

        while (!q.isEmpty()) {
            int node = q.poll();
            for (Integer neighbour : adj.get(node)) {
                if (cost[neighbour] > cost[node] + 1) {
                    cost[neighbour] = cost[node] + 1;
                    q.offer(neighbour);
                }
            }
        }
        
        for (int i = 0; i < n; i++)
            if (cost[i] ==  Integer.MAX_VALUE)
                cost[i] = -1;
        
        return cost;
    }
}