class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        return solve(nums, 0, 0, target);
    }
    private int solve(int[] nums, int curr, int currSum, int target) {
        if (curr == nums.length) {    
            return (currSum == target) ? 1 : 0;
        }
        int adder = solve(nums, curr + 1, currSum + nums[curr], target);
        int sub = solve(nums, curr + 1, currSum - nums[curr], target);
        return adder + sub;
    }
}