import java.util.Arrays;

class Solution {
    // Function to return max value that can be put in knapsack of capacity W.
    private static int[][] dp;
    static int knapSack(int W, int wt[], int val[]) {
        // your code here
        int n = wt.length;
        dp = new int[n + 1][W + 1];
        for (int[] x : dp)
            Arrays.fill(x, -1);
        return solve(0, wt, val, W);
    }
    private static int solve (int curr, int wt[], int val[], int W) {
        if (curr == wt.length)  return 0;
        if (dp[curr][W] != -1)  return dp[curr][W];
        int take = 0, nottake = 0;
        if (wt[curr] <= W) {
            take = val[curr] + solve(curr + 1, wt, val, W - wt[curr]);
        }
        nottake = solve(curr + 1, wt, val, W);
        return dp[curr][W] = Math.max(take, nottake);
    }
}
