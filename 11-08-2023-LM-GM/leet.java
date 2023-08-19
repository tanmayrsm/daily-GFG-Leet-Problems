class Solution {
    private static int[][] dp;
    public int change(int sum, int[] coins) {
        int N = coins.length;
        dp = new int[N + 1][sum + 1];
        for(int[] x : dp)
            Arrays.fill(x, -1);
        return solve(coins, 0, sum);
        
    }
    private static int solve(int[] coins, int curr, int sum) {
        if(curr >= coins.length || sum == 0) {
            if(sum == 0)    return 1;
            return 0;
        }
        if(dp[curr][sum] != -1)
            return dp[curr][sum];
        int take = 0; int nottake = 0;
        if(sum - coins[curr] >= 0)
            take = solve(coins, curr, sum - coins[curr]);  // take
        nottake = solve(coins, curr + 1, sum);
        return dp[curr][sum] = take + nottake; 
    }
}