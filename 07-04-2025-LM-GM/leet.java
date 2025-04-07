import java.util.Arrays;

class Solution {
    private static int reqSum;
    private static int[][] dp;
    public boolean canPartition(int[] nums) {
        int sum = Arrays.stream(nums).sum(), n = nums.length;
        if (sum % 2 != 0) {
            return false;
        }
        dp = new int[n][sum / 2];
        for(int[] d : dp)
            Arrays.fill(d, -1);
        reqSum = sum / 2;
        return solve(0, nums, 0) == 1;
    }
    private int solve(int curr, int[] nums, int currSum) {
        if (currSum ==  reqSum) return 1;
        if (curr == nums.length) {
            return (currSum == reqSum) ? 1 : 0;
        }
        if (dp[curr][currSum] != -1)    return dp[curr][currSum];
        int take = 0, nottake = 0;
        if (currSum + nums[curr] <= reqSum) {
            take = solve(curr + 1, nums, currSum + nums[curr]);
        }
        nottake = solve(curr + 1, nums, currSum); 
        return dp[curr][currSum] = ((take==1 || nottake==1) ? 1 : 0);
    }
}