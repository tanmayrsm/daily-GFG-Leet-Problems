class Solution {
    private static int[][] dp;
    public int combinationSum4(int[] nums, int target) {
        Arrays.sort(nums);
        dp = new int[nums.length][target];

        for(int[] x : dp)   Arrays.fill(x, -1);

        int ret = solve(nums, 0, 0, target);
        return ret == Integer.MAX_VALUE ? 0 : ret;
    }
    private static int solve(int[] nums, int index, int currSum, int target) {
        if(currSum == target)   return 1;
        if(index < 0 || index >= nums.length || nums[index] > target)    return Integer.MAX_VALUE;
        if(dp[index][currSum] != -1)    return dp[index][currSum];
        
        int take = Integer.MAX_VALUE, nottake = Integer.MAX_VALUE, takeAndLeft = Integer.MAX_VALUE;
        int furr = 0;
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] + currSum <= target) {
                int k = solve(nums, i, currSum + nums[i], target);
                if(k != Integer.MAX_VALUE) {
                    furr += k;
                }
            }
        }
        return dp[index][currSum] = furr;
    }
}