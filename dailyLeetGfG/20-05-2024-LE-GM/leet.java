class Solution {
    public int subsetXORSum(int[] nums) {
        return solve(0, 0, nums);
    }
    private static int solve(int x, int curr, int[] nums) {
        if(curr == nums.length) return x;
        int take = solve(x ^ nums[curr], curr + 1, nums);
        int notake = solve(x, curr + 1, nums);
        return take + notake;
    }
}