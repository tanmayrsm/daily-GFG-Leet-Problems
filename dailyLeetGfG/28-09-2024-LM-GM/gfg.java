
class Solution {
    private int[][] dp;
    public int minimizeCost(int k, int arr[]) {
        // code here
        dp = new int[arr.length + 1][k + 1];
        for(int[] x : dp)
            Arrays.fill(x, -1);
        return solve(0, k, arr);
    }
    private int solve(int curr, int k, int[] arr) {
        if (curr >= arr.length - 1)
            return 0;
        if (dp[curr][k] != -1)
            return dp[curr][k];
        int mino = Integer.MAX_VALUE;
        for(int i = curr + 1; i <= curr + k && i < arr.length; i++) {
            // int r = solve(i, k, arr);
            // if (r != Integer.MAX_VALUE)
            mino = Math.min(mino, Math.abs(arr[curr] - arr[i]) + solve(i, k, arr));
        }
        return dp[curr][k] = mino;
    }
}
