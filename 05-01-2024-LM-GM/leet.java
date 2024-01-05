class Solution {
    private static int[][] dp;
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        dp = new int[n + 1][n + 1];
        for(int[] x : dp)
            Arrays.fill(x, -1);
        return solve(nums, 0, -1);
    }
    private static int solve(int[] nums, int curr, int indexOfLastElemPicked) {
        if(curr == nums.length)
            return 0;
        if(dp[curr + 1][indexOfLastElemPicked + 1] != -1)
            return dp[curr + 1][indexOfLastElemPicked + 1];

        int n1 = 0;
        if(indexOfLastElemPicked == -1 || (indexOfLastElemPicked >= 0 && nums[indexOfLastElemPicked] < nums[curr]))
            n1 = 1 + solve(nums, curr + 1, curr);
        int n2 = 0 + solve(nums, curr + 1, indexOfLastElemPicked);
        
        return dp[curr + 1][indexOfLastElemPicked + 1] =  Math.max(n1, n2);
    }
}