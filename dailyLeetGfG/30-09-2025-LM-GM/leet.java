class Solution {
    public int triangularSum(int[] nums) {
        int n = nums.length, j = n;
        for (int k = 0; k < j; k++) {
            for (int i = 0; i < n - 1; i++) {
                nums[i] = (nums[i] + nums[i + 1]) % 10;
            }
            n--;
        }
        return nums[0];
    }
}