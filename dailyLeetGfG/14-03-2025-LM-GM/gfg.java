
class Solution {
    private static int[][] dp;
    public int count(int coins[], int sum) {
        int N = coins.length;
        dp = new int[N + 1][sum + 1];
        
        for(int j = 0; j < N + 1; j++)
            dp[j][sum] = 1;
        
        for(int i = N - 1; i >= 0; i--) {
            for(int j = sum - 1; j >= 0; j--) {
                dp[i][j] = (j + coins[i] <= sum ? dp[i][j + coins[i]] : 0) + dp[i + 1][j];
            }
        }
        
        return dp[0][0];
    }
    // private static long solve(int[] coins, int curr, int currsum) {
    //     if(curr >= coins.length || sum == currsum) {
    //         if(sum == currsum)    return 1;
    //         return 0;
    //     }
    //     if(dp[curr][currsum] != -1)
    //         return dp[curr][currsum];
    //     long take = 0; long nottake = 0;
    //     if(currsum + coins[curr] <= sum)
    //         take = solve(coins, curr, currsum + coins[curr]);  // take
    //     nottake = solve(coins, curr + 1, currsum);
    //     return dp[curr][currsum] = take + nottake; 
    // }
}