class Solution {
    public int countHillValley(int[] nums) {
        int ans = 0, n = nums.length;
        for (int i = 1; i < n - 1; i++) {
            if (nums[i] == nums[i - 1]) continue;
            int j = i + 1, k = i - 1;
            while(j < n && nums[i] == nums[j])  j++;
            if (j < n && nums[k] > nums[i] && nums[i] < nums[j]) ans++;
            else if (j < n && nums[k] < nums[i] && nums[i] > nums[j]) ans++;
        }
        return ans;
    }
}