class Solution {
    static int[][] dp;
    public int uniquePaths(int m, int n) {
        dp = new int[m][n];
        for(int[] x : dp)   Arrays.fill(x, -1);
        return solve(0, 0, m, n);
    }
    private static int solve(int x, int y, int m, int n) {
        if(x == m - 1 && y == n - 1)    return 1;
        if(!valid(x, y, n, m))  return 0;
        if(dp[x][y] != -1)  return dp[x][y];

        int takeright = solve(x, y + 1, m, n);
        int takedown = solve(x + 1, y, m, n);
        return dp[x][y] = takeright + takedown;
    }

    private static boolean valid(int x, int y, int m, int n) {
        if(x < 0 || y < 0 || x >= n || y >= m)  return false;
        return true;
    }
}