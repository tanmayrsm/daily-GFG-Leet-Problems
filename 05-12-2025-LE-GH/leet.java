class Solution {
    public int countPartitions(int[] nums) {
        int sum = Arrays.stream(nums).sum(), ans = 0, lSum = 0;
        for (int i = 0; i < nums.length; i++) {
            lSum += nums[i];
            sum -= nums[i];
            if (i >= 0 && i <= nums.length - 2 && (lSum - sum) % 2 == 0)  ans++;
        }
        return ans;
    }
}