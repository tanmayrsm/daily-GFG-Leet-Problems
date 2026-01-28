class Solution {
    public int countMaxOrSubsets(int[] nums) {
        int maxBitOr = 0;
        for(int num : nums)
            maxBitOr = maxBitOr | num;
        return solve(nums, 0, 0, maxBitOr);
    }
    private int solve(int[] nums, int curr, int currOr, int maxBitOr) {
        if(curr == nums.length) return 0;
        int take = 0, nottake = 0;
        if((currOr | nums[curr]) == maxBitOr || currOr == maxBitOr)
            take = 1 + solve(nums, curr + 1, currOr | nums[curr], maxBitOr);
        else take = solve(nums, curr + 1, currOr | nums[curr], maxBitOr);
        nottake = solve(nums, curr + 1, currOr, maxBitOr);
        return take + nottake;
    }
}