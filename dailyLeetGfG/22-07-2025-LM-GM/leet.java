class Solution {
    public int maximumUniqueSubarray(int[] nums) {
        Set<Integer> mp = new HashSet<>();
        int size = 0, r = 0, l = 0, n = nums.length, currMax = 0, ans = 0, curr = 0;
        while (r < n) {
            curr = nums[r];
            while (l < r && mp.contains(curr)) {
                currMax -= nums[l];
                mp.remove(nums[l]);
                l++;
            }
            while (r < n && !mp.contains(curr)) {
                mp.add(curr);
                currMax += curr;
                r++;
                if (r < n)
                    curr = nums[r];
            }
            ans = Math.max(ans, currMax);
        }
        return ans;
    }
}