class Solution {    // referred
    public int maximumScore(int[] nums, int k) {
        int min = nums[k];
        int left = k, right = k;
        int ans = nums[k];

        while (left > 0 || right < nums.length - 1) {
            int leftItem = left > 0 ? nums[left - 1] : 0;
            int rightItem = right < nums.length - 1 ? nums[right + 1] : 0;

            if (leftItem < rightItem) {
                right++;
                min = Math.min(min, nums[right]);
            } else {
                left--;
                min = Math.min(min, nums[left]);
            }

            ans = Math.max(ans, min * ((right - left) + 1));
        }

        return ans;
    }
}