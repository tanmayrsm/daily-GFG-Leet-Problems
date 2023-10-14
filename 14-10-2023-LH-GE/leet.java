public class Solution { // referred soln
    long[][] dp;

    public int paintWalls(int[] cost, int[] time) {
        int n = cost.length;
        dp = new long[n + 1][n + 1];

        for(long[] x : dp)   Arrays.fill(x, -1);

        long res = F(cost, time, 0, 0);
        return (int) res;
    }

    public long F(int[] cost, int[] time, int i, int t) {
        int n = cost.length;

        if (t >= n) {
            return 0;
        }
        if (i == n) {
            return Integer.MAX_VALUE;
        }
        if (dp[i][t] != -1) {
            return dp[i][t];
        }

        // if picked => t + 1 + time[i]
        // to ensure, we dont have t more than n/2 times indirectly
        dp[i][t] = Math.min(cost[i] + F(cost, time, i + 1, t + 1 + time[i]), F(cost, time, i + 1, t));
        return dp[i][t];
    }
}