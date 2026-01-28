class solve
{
    //Function to find the maximum possible amount of money we can win.
    private static int[][] dp;
    static long countMaximum(int n, int arr[]) {
        // Your code here
        dp = new int[n + 1][n + 1];
        for(int[] x : dp)
            // for(int[] y : x)
            Arrays.fill(x, -1);
        return solve(0, n - 1, arr);
    }
    
    private static int solve(int l, int r, int[] arr) {
        if(l > r)   return 0;
        if(dp[l][r] != -1) return dp[l][r];
        int takeLeft = arr[l] + Math.min(solve(l + 2, r, arr), solve(l + 1, r - 1, arr));
        int takeRight = arr[r] + Math.min(solve(l + 1, r - 1, arr), solve(l, r - 2, arr));
        return dp[l][r] = Math.max(takeLeft, takeRight);
    }
}