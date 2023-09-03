
class Solution {
    private static long[][] dp;
    public long count(int coins[], int N, int sum) {
        dp = new long[N + 1][sum + 1];
        for(long[] x : dp)
            Arrays.fill(x, -1);
        return solve(coins, 0, sum);
    }
    private static long solve(int[] coins, int curr, int sum) {
        if(curr >= coins.length || sum == 0) {
            if(sum == 0)    return 1;
            return 0;
        }
        if(dp[curr][sum] != -1)
            return dp[curr][sum];
        long take = 0; long nottake = 0;
        if(sum - coins[curr] >= 0)
            take = solve(coins, curr, sum - coins[curr]);  // take
        nottake = solve(coins, curr + 1, sum);
        return dp[curr][sum] = take + nottake; 
    }
}