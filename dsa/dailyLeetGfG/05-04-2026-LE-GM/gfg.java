class Solution {
    int[][] dp;
    public int totalWays(int[] arr, int target) {
        // code here
        int sum = 0;
        dp = new int[arr.length][2002];
        for(int[] x : dp)   Arrays.fill(x, -1);
        for(int x : arr)    sum += x;
        return solve(arr, sum, 0, target);
    }
    private int solve(int[] arr, int cSum, int curr, int target) {
        if(curr == arr.length) {
            return (cSum == target) ? 1 : 0;
        }
        if(dp[curr][cSum + 1000] != -1) return dp[curr][cSum + 1000];
        int takeNeg = 0, takePos = 0;
        takeNeg = solve(arr, cSum - arr[curr] - arr[curr], curr + 1, target);
        takePos = solve(arr, cSum, curr + 1, target);
        return dp[curr][cSum + 1000] = takeNeg + takePos;
    }
}