//User function Template for Java

class Solution
{
    private static int max = Integer.MAX_VALUE;
    private static int[][] dp;
    public int min_operations(int []nums) {
        // Code here
        // LIS solves 90% of problem, with a twist in a condition
        int n = nums.length;
        dp = new int[n + 1][n + 1];
        for(int[] x : dp)   Arrays.fill(x, -1);
        
        return nums.length - solve(0, nums, -1);
    }
    private static int solve(int curr, int[] nums, int last) {
        if(curr == nums.length) return 0;
        if(dp[curr + 1][last + 1] != -1) return dp[curr + 1][last + 1];
        int take = 0, nottake = 0;
        if(last == -1 || (nums[last] < nums[curr] && nums[curr] - nums[last] >= curr - last)) { // refer example below for why I added second condition
            take = 1 + solve(curr + 1, nums, curr);
        }
        nottake = solve(curr + 1, nums, last);
        return dp[curr + 1][last + 1] = Math.max(take, nottake);
    }
}

// 1 2 3 3 3 3 4
// 1 2 3 4 is LIS

// but ans is 4 (7 - 3)
// hence actual LIS has to be '1 2 3' for above example
