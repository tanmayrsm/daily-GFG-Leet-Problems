class Solution {
    public int xorAfterQueries(int[] nums, int[][] queries) {
        int n = nums.length;
        int mod = (int) 1e9 + 7, ans = 0;
        for(int[] q : queries) {
            for(int i = q[0]; i <= q[1]; i+= q[2]) {
                long g = (long)nums[i] * (long)q[3];
                nums[i] = (int)(g % mod);
            }
        }
        for(int x : nums)   ans ^= x;
        return ans;
    }
}