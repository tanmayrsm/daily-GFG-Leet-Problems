class Solution {
    private static int[] dp;
    public int rob(int[] nums) {
        dp = new int[nums.length];
        Arrays.fill(dp, -1);
        return solve(0, nums, nums.length);
    }
    private static int solve(int curr, int[] nums, int n) {
        if(curr >= n)   return 0;
        if(dp[curr] != -1)  return dp[curr];
        int take = Integer.MIN_VALUE, nottake = Integer.MIN_VALUE;
        take = nums[curr] + solve(curr + 2, nums, n);
        nottake = solve(curr + 1, nums, n);
        return dp[curr] = Math.max(take, nottake);
    }
}