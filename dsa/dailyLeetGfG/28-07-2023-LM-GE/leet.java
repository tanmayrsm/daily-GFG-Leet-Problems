class Solution {
    private static int[][] dp;
    public boolean PredictTheWinner(int[] nums) {
        int n = nums.length;
        dp = new int[n][n];
        for(int[] x :  dp)
            Arrays.fill(x, -1);
        
        return solve(nums, 0, n - 1) >= 0 ? true : false;
    }
    private static int solve(int[] nums, int l, int r) {
        if(dp[l][r] != -1)
            return dp[l][r];
        if(l == r) 
            return nums[l];
        int left = nums[l] - solve(nums, l + 1, r);
        int right = nums[r] - solve(nums, l, r - 1);
        return dp[l][r] = Math.max(left, right);
    }
}