
class Solution {
    public int minRemoval(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length, l = 0, ans = Integer.MAX_VALUE;
        if (n == 1) return 0;
        for (int i = 0; i < n; i++) {
            while((long) nums[l] * k < nums[i])    l++;
            ans = Math.min(ans, n - (i - l) - 1);
        }
        return ans;
    }
}

//1 2 6 9
//k = 3
//
//max <= k * min
//9 <= 3 * 1




