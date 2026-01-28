
class Solution {
    public long maximumSubarraySum(int[] nums, int k) {
        Map<Integer, Integer> mp = new HashMap<>();
        int r = 0, l = 0, n = nums.length;
        long ans = 0, curr = 0;
        while (r < n && l < n) {
            int duplicateFoundIndex = -1;
            while (r < n && r - l < k) {
                if(mp.containsKey(nums[r])) {   // if duplicate elemenet found, break
                    duplicateFoundIndex = mp.get(nums[r]);  
                    break;
                }
                curr += nums[r];
                mp.put(nums[r], r);
                r++;
            }

            if(duplicateFoundIndex == -1) {
                if (r - l == k)
                    ans = Math.max(ans, curr);
                mp.remove(nums[l]);
                curr -= nums[l];
                l++;
            } else if (duplicateFoundIndex != -1) {
                // now remove all elements before this duplicate index
                while (l <= duplicateFoundIndex) {
                    mp.remove(nums[l]);
                    curr -= nums[l];
                    l++;
                }
            }
        }
        return ans;
    }
}