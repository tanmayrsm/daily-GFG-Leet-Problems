class Solution {
    private static int[][] dp;
    public int maxSumAfterPartitioning(int[] arr, int k) {
        dp = new int[arr.length + 1][k + 1];
        for(int[] x : dp)
            Arrays.fill(x, -1);
        return solve(0, 0, -1, k, arr);
    }
    private static int solve(int curr, int currLen, int currMax, int k, int[] arr) {
        if(curr == arr.length)  return currMax * currLen;
        if(dp[curr][currLen] != -1) return dp[curr][currLen];
        int take = 0, nottake = 0;
        // take
        if(currLen + 1 <= k) {
            take = solve(curr + 1, currLen + 1, Math.max(currMax, arr[curr]), k, arr);
        }
        // not take
        if(currLen > 0) {
            nottake = currMax * currLen + solve(curr + 1, 1, arr[curr], k, arr);
        }
        return dp[curr][currLen] = Math.max(take, nottake);
    }
}