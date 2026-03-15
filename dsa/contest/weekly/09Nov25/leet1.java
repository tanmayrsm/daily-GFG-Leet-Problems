class Solution {
    public long maximumMedianSum(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length, j = 0;
        long ans = 0;
        for (int i = n - 1; i >= 0 && i - 1 > j; i -= 2) {
            if (i - 1 >= 0) ans += nums[i - 1];
            j++;
        }
        return ans;
    }
}

// 2 1 3 2 1 3
// 1 1 2 2 3 3




