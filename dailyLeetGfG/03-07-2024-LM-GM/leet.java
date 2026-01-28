class Solution {
    public int minDifference(int[] nums) {
        int n = nums.length, mino = Integer.MAX_VALUE;
        if(n <= 3)    return 0;
        Arrays.sort(nums);
        int subArraySize = n - 3;
        // sliding window, where u need to find n - 3 size subarray with min diff of [0] and [last] elem inside this subarray
        for(int i = 0; i <= n - subArraySize; i++) {
            int last = i + subArraySize - 1;
            mino = Math.min(mino, nums[last] - nums[i]);
        }
        return mino;
    }
}