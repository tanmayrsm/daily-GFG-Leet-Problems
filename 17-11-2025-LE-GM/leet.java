class Solution {
    public boolean kLengthApart(int[] nums, int k) {
        int n = nums.length, prev = -1;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 1) {
                if (prev == -1) {
                    prev = i;
                } else if (i - prev - 1 <k) {
                    return false;
                }
                prev = i;
            }
        }
        return true;
    }
}