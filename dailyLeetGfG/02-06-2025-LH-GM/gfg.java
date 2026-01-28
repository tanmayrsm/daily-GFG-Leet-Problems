class Solution {
    private static int ans;
    private static int[][] dp;
    private static long M = 1000000007;
    static int uniquePaths(int[][] grid) {
        // code here
        ans = 0;
        int n = grid.length, m = grid[0].length;
        dp = new int[n][m];
        if(grid[0][0] == 1 || grid[n-1][m-1] == 1)
            return ans;
        for(int i = 0; i< n; i++)
            Arrays.fill(dp[i], -1);

        return (int)(dfs(0, 0, n, m, grid) % M);
    }
    private static int dfs(int x, int y, int n, int m, int[][] grid) {
        if(x >= n || y >= m)
            return 0;
        else if(grid[x][y] == 1)
            return 0;

        if(x == n - 1 && y == m - 1) {
            return 1;
        }

        if(dp[x][y] != -1)  return dp[x][y];

        dp[x][y] = (int)((dfs(x, y + 1, n, m, grid) + dfs(x + 1, y, n, m, grid)) % M);
        return dp[x][y];
    }

};