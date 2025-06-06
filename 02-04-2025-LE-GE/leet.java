class Solution {
    public long maximumTripletValue(int[] nums) {
        int n = nums.length;
        long ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    long curr = (nums[i] - nums[j]) * (long)nums[k];    // explicit conversion
                    ans = Math.max(ans, curr);
                }
            }
        }
        return ans;
    }
}

// (nums[i] - nums[j]) * nums[k]