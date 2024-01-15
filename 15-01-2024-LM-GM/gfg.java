
//User function Template for Java
class Solution {
    // intuition - 0/1 knapsack
    
    private static int[][] dp;
    public int max_courses(int n, int total, int[] cost) {
        // code here
        dp = new int[n + 1][total + 1];
        for(int[] x : dp)
            Arrays.fill(x, -1);
        return solve(0, total, n, cost);
    }
    
    private static int solve(int curr, int currAmt, int n, int[] cost) {
        if(curr >= n)   return 0;
        
        if(dp[curr][currAmt] != -1)  return dp[curr][currAmt];
        int take = Integer.MIN_VALUE, nottake = Integer.MIN_VALUE;
        if(currAmt >= cost[curr]) {
                take = 1 + solve(curr + 1, currAmt - (cost[curr] - (int)Math.floor(cost[curr] * 0.9)), n, cost);
        }
        nottake = solve(curr + 1, currAmt, n, cost);
        return dp[curr][currAmt] = Math.max(take, nottake);
    }
}