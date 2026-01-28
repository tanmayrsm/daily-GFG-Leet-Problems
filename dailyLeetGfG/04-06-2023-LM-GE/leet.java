class Solution {
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        boolean[] vis = new boolean[n + 1];
        int prov = 0;
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                if(i == j)
                    isConnected[i][j] = 0;

        for(int i = 0; i < n; i++){
            if(!vis[i]) {
                prov++;
                dfs(isConnected, n, vis, i);
            }
        }
        return prov;
    }
    private static void dfs(int[][] isConnected, int n, boolean[] vis, int node) {
        vis[node] = true;
        for(int i = 0; i < n; i++) {
            int neigh = isConnected[node][i];
            if(neigh == 1 && !vis[i])
                dfs(isConnected, n, vis, i);
        }
    }
}