import java.util.Arrays;

class Solution {
    public int maximumBeauty(int[] nums, int k) {
        Arrays.sort(nums);
        int l = 0, r = 0, n = nums.length, maxo = 0;
        while (r < n) {
            int min = nums[l] + k;
            while (r < n && nums[r] - k <= min) {
                r++;
            }
            if (nums[r] - k <= min)
                maxo = Math.max(maxo, r - l);
            while (l < r && nums[l] + k < nums[r] - k) {
                l++;
            }
        }
        return maxo;
    }
}