
class Solution {
    public int findMinCycle(int V, int[][] edges) {
        // Initialize adjacency matrix with infinity
        int[][] dist = new int[V][V];
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                dist[i][j] = (i == j) ? 0 : 100000;
            }
        }
        
        // Fill adjacency matrix with edge weights
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], w = edge[2];
            dist[u][v] = Math.min(dist[u][v], w);
            dist[v][u] = Math.min(dist[v][u], w); // Since graph is undirected
        }
        
        int minCycle = Integer.MAX_VALUE;
        
        // Try each edge as part of the cycle
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], w = edge[2];
            
            // Temporarily remove the edge
            dist[u][v] = 100000;
            dist[v][u] = 100000;
            
            // Run Floyd-Warshall to find shortest paths
            for (int k = 0; k < V; k++) {
                for (int i = 0; i < V; i++) {
                    for (int j = 0; j < V; j++) {
                        if (dist[i][k] != 100000 && dist[k][j] != 100000) {
                            dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                        }
                    }
                }
            }
            
            // Check if there's a path from v to u
            if (dist[v][u] != 100000) {
                minCycle = Math.min(minCycle, w + dist[v][u]);
            }
            
            // Restore the edge
            dist[u][v] = w;
            dist[v][u] = w;
            
            // Reset other distances for next iteration
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    if (i != j) dist[i][j] = 100000;
                }
            }
            // Rebuild adjacency matrix
            for (int[] e : edges) {
                dist[e[0]][e[1]] = Math.min(dist[e[0]][e[1]], e[2]);
                dist[e[1]][e[0]] = Math.min(dist[e[1]][e[0]], e[2]);
            }
        }
        
        return minCycle == Integer.MAX_VALUE ? -1 : minCycle;
    }
};