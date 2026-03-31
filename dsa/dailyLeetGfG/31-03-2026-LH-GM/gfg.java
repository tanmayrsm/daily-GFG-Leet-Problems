class Solution {
    int min = Integer.MIN_VALUE;
    int[][] dp;
    public int maxProfit(int arr[], int k) {
        // Code here
        int n = arr.length;
        dp = new int[n][2];
        for(int[] x : dp)
            Arrays.fill(x, -1);
        return solve(arr, k, 0, 0);
    }
    // status = 1, already bought
    private int solve(int[] arr, int k, int curr, int status) {
        if(curr == arr.length) {
            if(status == 1) {
                return min;
            }
            return 0;
        }
        if(dp[curr][status] != -1) return dp[curr][status];
        int take = 0, nottake = 0;
        if(status == 0){
            int r = solve(arr, k, curr + 1, 1); // buy
            if(r != min)
                take = r - arr[curr];
        } else {
            int r = solve(arr, k, curr + 1, 0); // sell
            if(r != min)
                take = r + arr[curr] - k;
        }
        nottake = solve(arr, k, curr + 1, status);
        return dp[curr][status] = Math.max(take, nottake);
    }
}