class Solution {
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        // all pairs shortest
        int[][] d = new int[n][n];
        int[][] sp = new int[n][n];
        int node = -1, smallest = Integer.MAX_VALUE;
        
        for (int i = 0; i < n; i++) {
            Arrays.fill(d[i], Integer.MAX_VALUE);
            Arrays.fill(sp[i], Integer.MAX_VALUE);
        }

        for (int[] edge : edges) {
            d[edge[0]][edge[1]] = edge[2];
            d[edge[1]][edge[0]] = edge[2];
            
        }

        for (int k = 0; k < n; k++) 
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                {
                    if (i != j && d[i][k] != Integer.MAX_VALUE && d[k][j] != Integer.MAX_VALUE && d[i][k] + d[k][j] < d[i][j])
                        d[i][j] = d[i][k] + d[k][j];
                }

        for (int i = 0; i < n; i++) {
            int neighs = 0;
            for (int j = 0; j < n; j++) {
                if (d[i][j] <= distanceThreshold)
                    neighs++;
            }
            if (neighs <= smallest) {
                smallest = neighs;
                node = i;
            }
        }

        return node;
            
    }
}