class Solution {
    int MOD = 1000000007;
    int[][][] dp;
    public int countStableSubsequences(int[] nums) {
        int n = nums.length;
        dp = new int[n][3][3];
        for (int[][] x : dp) for(int[] y : x)  Arrays.fill(y, -1);
        return solve(nums, 0, -1, -1) - 1;  // -1 as it also counts empty subset
    }
    private int solve(int[] nums, int curr, int last2, int last1) {
        if (curr == nums.length)    return 1;
        if (dp[curr][last1 + 1][last2 + 1] != -1)   return dp[curr][last1 + 1][last2 + 1];
        int currParity = (nums[curr] % 2 == 0) ? 0 : 1;
        long take = 0, nottake = 0;
        if (last1 == -1) {
            take = solve(nums, curr + 1, last2, currParity);
        } else if (last2 == -1) {
            take = solve(nums, curr + 1, last1, currParity);
        } else {
            if (last1 == last2 && last1 == currParity)  {
                // do nothing
            } else {
                take = solve(nums, curr + 1, last1, currParity);
            }
        }
        nottake = solve(nums, curr + 1, last2, last1);
        return dp[curr][last1 + 1][last2 + 1] = (int)((take + nottake) % MOD);
    }
}