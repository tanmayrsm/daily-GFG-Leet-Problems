class Solution {
    private static int[][] dp;
    public int findMinCost(String x, String y, int costX, int costY) {
        int n = x.length(), m = y.length();
        dp = new int[n + 1][m + 1];
        for(int[] p : dp)   Arrays.fill(p, -1);
        return solve(0, 0, x, y, costX, costY, x.length(), y.length());
    }
    private static int solve(int l, int r, String x, String y, int costX, int costY, int n, int m) {
        if(l == n && r == m)    return 0;
        if(dp[l][r] != -1)  return dp[l][r];
        if(l == n && r < m)
            return dp[l][r] = costY + solve(l, r + 1, x, y, costX, costY, n, m);
        else if (l < n && r == m)
            return dp[l][r] = costX + solve(l + 1, r, x, y, costX, costY, n, m);
            
        if(x.charAt(l) == y.charAt(r)) {
            return dp[l][r] = solve(l + 1, r + 1, x, y, costX, costY, n, m);
        } else {
            return dp[l][r] = Math.min(costX + solve(l + 1, r, x, y, costX, costY, n, m), 
                costY + solve(l, r + 1, x, y, costX, costY, n, m));
        }
    }
}