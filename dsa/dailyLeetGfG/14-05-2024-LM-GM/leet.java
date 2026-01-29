
class Solution {
    private static int[][] dir = new int[][] {{0,1}, {0, -1}, {1,0}, {-1, 0}};
    private static boolean[][] vis;
    private static int[][] dp;
    private static int n, m;

    public int getMaximumGold(int[][] grid) {
        // code here
        int ans = 0;
        n = grid.length; m = grid[0].length;
        vis = new boolean[n][m];
        dp = new int[n][m];
        for(int[] d : dp)
            Arrays.fill(d, -1);
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++)
                if(grid[i][j] > 0) {
                    int res = solve(i, j, grid);
                    ans = Math.max(ans, solve(i, j, grid));
                }
        }
        return ans;
    }
    private static int solve(int x, int y, int[][] grid) {
        
        // if(dp[x][y] != -1)  return dp[x][y];

        int ret = grid[x][y], currAns = 0;
        vis[x][y] = true;
        for(int[] d : dir) {
            int xx = x + d[0], yy = y + d[1];
            if(xx >= 0 && yy >= 0 && xx < n && yy < m && !vis[xx][yy] && grid[xx][yy] > 0) {
                currAns = Math.max(currAns, solve(xx, yy, grid));
            }
        }
        vis[x][y] = false;
        return dp[x][y] = ret + currAns;
    }
}