class Solution {
    static long[][] dp;
    static int mod = 1000000007;
    public int numRollsToTarget(int n, int k, int target) {
        dp = new long[n + 1][target + 1];
        for(long[] x :  dp)  Arrays.fill(x, -1);
        return (int)solve(n, target, k, 1);
    }
    private static long solve(int n, int currSum, int k, int currK) {
        if(currSum == 0 && n == 0)    return 1;
        if(currSum <= 0 || n <= 0) return 0;
        if(dp[n][currSum] != -1)    return dp[n][currSum];
        long takeCurr = 0, takeNext = 0;
        for(int i = 1; i <= k; i++) {
            if(currSum - i >= 0)
                takeCurr += solve(n - 1, currSum - i, k, i);
                takeCurr %= mod;
        }
        return dp[n][currSum] = takeCurr;
    }
}