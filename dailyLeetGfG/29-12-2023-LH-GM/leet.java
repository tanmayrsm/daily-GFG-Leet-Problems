class Solution {
    private static int n;
    private static int[][][] dp;
    public int minDifficulty(int[] jobDifficulty, int d) {
        n = jobDifficulty.length;
        dp = new int[n + 1][d + 1][1001];
        for(int[][] x : dp)   
            for(int[] y : x)
                Arrays.fill(y, -1);
        int res = solve(jobDifficulty, d, 0, 0);
        return res == Integer.MAX_VALUE ? -1 : res;
    }
    private static int solve(int[] jobDifficulty, int remD, int curr, int currMax) {
        if(remD == 0 && curr == n)  return currMax;
        else if (curr == n && remD > 0) return Integer.MAX_VALUE;
        if(dp[curr][remD][currMax] != -1)    return dp[curr][remD][currMax];
        
        int take = Integer.MAX_VALUE, notTake = Integer.MAX_VALUE; 

        if(n - curr >= remD)
            take = solve(jobDifficulty, remD, curr + 1, Math.max(currMax, jobDifficulty[curr]));
        else return take;
        if(remD > 0)
            notTake = currMax + solve(jobDifficulty, remD - 1, curr + 1, jobDifficulty[curr]);
        
        return dp[curr][remD][currMax] = Math.min(take, notTake);
    }
}