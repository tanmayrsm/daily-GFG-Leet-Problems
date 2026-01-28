class Solution {
    static int mod = 1000000007;
    public int countGoodStrings(int low, int high, int zero, int one) {
        int[] dp = new int[high + 1];
        Arrays.fill(dp, -1);
        return solve(0, dp, low, high, zero, one);
    }
    private static int solve(int len, int[] dp, int low, int high, int zero, int one) {
        if(len > high)
            return 0;
        if (dp[len] != -1)
            return dp[len];
        dp[len] = len >= low ? 1 : 0;
        dp[len] +=( solve(len + zero, dp, low, high, zero, one) + solve(len + one, dp, low, high, zero, one)) % mod;
        return dp[len] % mod;
    }
}