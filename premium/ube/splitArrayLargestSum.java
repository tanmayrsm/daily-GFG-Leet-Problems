import java.util.Arrays;

class Solution {
    public int splitArray(int[] nums, int k) {
        int l = Arrays.stream(nums).min(), r = Arrays.stream(nums).sum(), ans = Integer.MAX_VALUE;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (checkIfCanSplit(nums, k, mid)) {
                ans = mid;
                r = mid - 1;
            } else l = mid + 1;
        }
        return ans;
    }
    private static boolean checkIfCanSplit(int[] nums, int k, int number) {
        int currSum = 0;
        for (int i = 0; i < nums.length; i++) {
            int rem = nums.length - 1 - i;
            if (rem >= k && nums[i] + currSum <= number) {  // I can accomodate more nos
                currSum += nums[i];
            } else {
                k--;
                currSum = nums[i];
            }
            if (nums[i] > number)   return false;
        }
        return k == 0;
    }
}