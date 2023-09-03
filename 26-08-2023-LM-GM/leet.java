class Solution {
    static int n;
    static int[][] dp;
    public int findLongestChain(int[][] pairs) {
        Arrays.sort(pairs, (x, y) -> Integer.valueOf(x[0]).compareTo(y[0]));
        n = pairs.length;
        dp = new int[n + 1][n + 1];
        for(int[] x : dp)
            Arrays.fill(x, -1);
        return solve(pairs, -1, 0);
    }

    private static int solve(int[][] pairs, int lastPair, int currPair) {
        if(currPair == pairs.length)
            return 0;
        if(dp[currPair + 1][lastPair + 1] != -1)
            return dp[currPair + 1][lastPair + 1];

        int take = 0, nottake = 0;
        if(lastPair == -1 || (currPair < n && pairs[currPair][0] > pairs[lastPair][1]))
            take = 1 + solve(pairs, currPair, currPair + 1);
        nottake = solve(pairs, lastPair, currPair + 1);
        return dp[currPair + 1][lastPair + 1] = Math.max(take, nottake);
    }
}