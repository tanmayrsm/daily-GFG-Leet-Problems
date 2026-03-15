class Solution {
    static int[][] dp;
    static int noOfWays(int m, int n, int x) {
        // code here
        dp = new int[n + 1][x + 1];
        for(int[] p : dp)   Arrays.fill(p, -1);
        return solve(m, n, x);
    }
    private static int solve(int m, int n, int x) {
        if(n == 0)  {
            return (x == 0) ? 1 : 0;
        }
        if(dp[n][x] != -1)  return dp[n][x];

        int take = 0;
        for(int i = 1; i <= m; i++) {
            if(x - i >= 0) {
                take += solve(m, n - 1, x - i);
            }
        }
        return dp[n][x] = take;
    }
};