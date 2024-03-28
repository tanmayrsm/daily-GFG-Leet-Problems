class Solution {
    public int maxSubarrayLength(int[] nums, int k) {
        Map<Integer, Integer> mp = new HashMap<>();
        int l = 0, r = 0, ans = 0, n = nums.length;
        while(r < n) {
            while(r < n && (mp.get(nums[r]) == null  || mp.get(nums[r]) + 1 <= k)) {
                mp.put(nums[r], mp.getOrDefault(nums[r], 0) + 1);
                r++;
            }
            ans = Math.max(ans, r - l);
            int lookFor = -1;
            if(r < n) {
                lookFor = nums[r];
                mp.put(nums[r], mp.getOrDefault(nums[r], 0) + 1);
                r++;
            }
            while(l < r && lookFor != -1 && mp.get(nums[l]) != null && mp.get(nums[l]) != 0 && mp.get(lookFor) > k) {
                mp.put(nums[l], mp.get(nums[l]) - 1);
                l++;
            }
            // ans = Math.max(ans, r - l);
            // System.out.println(l + "::" + r + "::" + mp);
        }
        return ans;
    }
}