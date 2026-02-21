class Solution {
    public int countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length, ans = 0;
        for (int i = 0; i < n; i++) {
            int ct = 0;
            for(int j = i; j < n; j++) {
                if(nums[j] == target)   ct++;
                if(ct * 2 > (j - i + 1)) {
                    ans++;
                }
            }
        }
        return ans;
    }
}

// 1 2 3 2 2 4 5 1 2 1
// - - . - - . . - - -