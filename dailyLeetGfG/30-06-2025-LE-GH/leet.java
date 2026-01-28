class Solution {
    public int findLHS(int[] nums) {
        Arrays.sort(nums);
        int r = 1, l = 0, n = nums.length, ans = 0;
        while(r < n && nums[r] == nums[r - 1])  r++;
        int oldR = r;
        while (r < n) {
            oldR = r;
            while (r < n && nums[r] == nums[oldR]) {
                r++;
            }
            if (nums[oldR] - nums[l] == 1)
                ans = Math.max(ans, r - l);
            l = oldR;
        }
        return ans;
    }
}