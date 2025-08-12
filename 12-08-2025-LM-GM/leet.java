class Solution {
    int[][] dp;
    int mod = (int)1e9 + 7;;
    public int numberOfWays(int n, int x) {
        dp = new int[n + 1][n + 1];
        for (int[] g : dp)
            Arrays.fill(g, -1);
        return solve(0, 1, n, x);
    }
    private int solve(int curr, int no, int n, int x) {
        if (curr == n)  return 1;
        if (curr > n || no > n || curr < 0)   return 0;
        if (dp[no][curr] != -1)   return dp[no][curr];
        int next = (int)Math.pow(no, x);
        int take = solve((curr + next) % mod, no + 1, n, x);
        int nottake = solve(curr, no + 1, n, x);
        return dp[no][curr] = (int)((take + nottake) % mod);
    }
}