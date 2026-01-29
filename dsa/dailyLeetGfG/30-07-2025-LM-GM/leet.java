class Solution {
    public int longestSubarray(int[] nums) {
        int maxo = Arrays.stream(nums).max().getAsInt(), n = nums.length, ans = 1;
        for (int i = 0; i < n; ) {
            int j = i + 1, len = 1;
            while (nums[i] == maxo && j < n && nums[j] == maxo) {
                len++;
                j++;
            }
            ans = Math.max(ans, len);
            i = j;
        }
        return ans;
    }
}

// any no, u do &, it will decrease, never will it increase, unless u & same nos, which result in same no
// so max & of any array = max(arr), and u need to find longest subarray with that no