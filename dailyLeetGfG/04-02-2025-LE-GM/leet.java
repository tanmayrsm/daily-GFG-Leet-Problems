class Solution {
    public int maxAscendingSum(int[] nums) {
        int tempSum = nums[0], sum = nums[0];
        for(int i = 1; i < nums.length; i++) {
            if(nums[i] > nums[i - 1]) {
                tempSum += nums[i];
            } else {
                tempSum = nums[i];
            }
            sum = Math.max(tempSum, sum);
        }
        return sum;
    }
}