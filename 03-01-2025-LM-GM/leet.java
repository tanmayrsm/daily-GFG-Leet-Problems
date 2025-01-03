class Solution {
    public int waysToSplitArray(int[] nums) {
        long sum = Arrays.stream(nums).mapToLong(i -> i).sum();
        long curr = 0;
        int n = nums.length, ans = 0;
        for(int i = 0; i < n - 1; i++) {
            curr += nums[i];
            if (curr >= (sum - curr)) {
                ans++;
            }
        }
        return ans;
    }
}