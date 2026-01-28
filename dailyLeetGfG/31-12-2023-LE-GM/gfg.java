
class Solution {
    private static int[][] dp;
    public static boolean isPossible(int N, int[] coins) {
        // code here
        dp = new int[N + 1][2025];
        for(int[] x :  dp)
            Arrays.fill(x, -1);
        return solve(N - 1, 0, coins) == 1 ? true : false;
    }
    private static int solve(int currN, int currSum, int[] coins) {
        if(currSum > 0 && (currSum == 2024 || currSum % 20 == 0 || currSum % 24 == 0))
            // System.out.println("cS::" + currSum + "::" + currN);
            return 1;
        
        if(currN < 0)   return Integer.MAX_VALUE;
        if(dp[currN][currSum] != -1)    return dp[currN][currSum];
        
        int take = Integer.MAX_VALUE, nottake = Integer.MAX_VALUE;
        take = solve(currN - 1, currSum + coins[currN], coins);
        nottake = solve(currN - 1, currSum, coins);
        return dp[currN][currSum] = (take == 1 || nottake == 1) ? 1 : 0;
    }
}