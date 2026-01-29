class Solution {
    static int n, mod = 1000000007;
    static long[][] dp;
    public int numWays(int steps, int arrLen) {
        n = arrLen;
        dp = new long[steps + 1][Math.min(steps, arrLen) + 1];
        for(long[] x : dp)   Arrays.fill(x, -1);
        return (int)solve(steps, 0);
    }
    private long solve(int steps, int curr) {
        if(curr < 0 || curr >= n)   return Integer.MIN_VALUE;
        if(steps == 0) {
            if(curr == 0)   return 1;
            return Integer.MIN_VALUE;
        }
        if(dp[steps][curr] != -1)   return dp[steps][curr] % mod;

        long stay = solve(steps - 1, curr);
        long moveLeft = solve(steps - 1, curr - 1);
        long moveRight = solve(steps - 1, curr + 1);
        
        return dp[steps][curr] = (
            (stay == Integer.MIN_VALUE ? 0 : stay % mod) + 
            (moveLeft == Integer.MIN_VALUE ? 0 : moveLeft % mod) + 
            (moveRight == Integer.MIN_VALUE ? 0 : moveRight % mod)
        ) % mod;
    }
}