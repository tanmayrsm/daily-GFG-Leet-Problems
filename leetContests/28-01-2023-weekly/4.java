class Solution {
    public int minOrAfterOperations(int[] nums, int k) {
        return solve(nums, 0, k);
    }
    private static int solve(int[] nums, int curr, int currK) {
        if(curr == nums.length || currK == 0) {
            int rem = 0;
            for(int x :  nums) {
                if(x > 0)
                    rem = rem | x;
            }
            return rem;
        }
        int take = Integer.MAX_VALUE, nottake = Integer.MAX_VALUE;

        // take
        if(curr + 1 < nums.length) {
            int temp = nums[curr], temp2 = nums[curr + 1];
            nums[curr + 1] &= nums[curr];
            nums[curr] = -1;
            take = solve(nums, curr + 1, currK - 1);

            nums[curr] = temp;
            nums[curr + 1] = temp2;
        }
        nottake = solve(nums, curr + 1, currK);
        return Math.min(take, nottake);
    }
}