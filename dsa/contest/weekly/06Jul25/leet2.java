class Solution {
    long[][] dp;
    public long minCost(int m, int n, int[][] waitCost) {
        dp = new long[m][n];
        for (long[] x : dp)
            Arrays.fill(x, -1);
        return solve(0, 0, m, n, waitCost, 1);
    }
    private long solve(int x, int y, int m, int n, int[][] waitCost, int time) {
        if (x == m - 1 && y == n - 1)   return (m) * (n);
        if (dp[x][y] != -1) return dp[x][y];
        long goRight = Long.MAX_VALUE, goDown = Long.MAX_VALUE;
        if (y + 1 < n) {
            goRight = (time % 2 == 0 ? waitCost[x][y] : 0) + (x + 1)*(y + 1) + solve(x, y + 1, m, n, waitCost,
                    (time%2 == 0 ? (time + 1) : time) + 1);
        }
        if (x + 1 < m) {
            goDown = (time % 2 == 0 ? waitCost[x][y] : 0) + (x + 1)*(y + 1) + solve(x + 1, y, m, n, waitCost,
                    (time%2 == 0 ? (time + 1) : time) + 1);
        }
        // System.out.println(goRight + "::" + goDown);
        return dp[x][y] = Math.min(goRight, goDown);
    }
}