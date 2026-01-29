class Solution {
    public int maxScoreSightseeingPair(int[] nums) {
        int cMax = nums[0] - 1, ans = 0;
        for(int i = 1; i < nums.length; i++) {
            ans = Math.max(ans, nums[i] + cMax);
            cMax = Math.max(cMax - 1, nums[i] - 1);
        }
        return ans;
    }
}