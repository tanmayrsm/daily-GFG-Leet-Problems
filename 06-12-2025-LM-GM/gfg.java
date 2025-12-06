
class Solution {
    private int[][] dp;
    public int maximumAmount(int arr[]) {
        // code here
        int n = arr.length;
        dp = new int[n][n];
        for (int[] x : dp)  Arrays.fill(x, -1);
        return solve(arr, 0, arr.length - 1);
    }
    private int solve(int[] arr, int l, int r) {
        if (l > r)  return 0;
        if (dp[l][r] != -1) return dp[l][r];
        int takeL = 0, takeR = 0;
        // int whoNext = (who == 1) ? 0 : 1;
        takeL = arr[l] + Math.min(solve(arr, l + 2, r),
                solve(arr, l + 1, r - 1));
        takeR = arr[r] + Math.min(solve(arr, l + 1, r - 1),
                solve(arr, l, r - 2));
        return dp[l][r] = Math.max(takeL, takeR);
    }
}
