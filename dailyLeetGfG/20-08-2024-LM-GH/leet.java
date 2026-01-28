class Solution {
    private static int[][][] dp;
    public int stoneGameII(int[] piles) {
        int n = piles.length;
        dp = new int[2][n + 1][n + 1];
        for (int[][] x : dp)
            for (int[] y : x)
                Arrays.fill(y, -1);
        return solve(1, piles, 0, 1);
    }
    private static int solve (int alice, int[] piles, int curr, int M) {
        if (curr == piles.length)   return 0;
        if (dp[alice][curr][M] != -1) return dp[alice][curr][M];
        int res = (alice == 1 ? 0 : Integer.MAX_VALUE), total = 0;

        for (int x = 1; x < 2 * M + 1; x++) {
            if (curr + x > piles.length)    break;
            total += piles[curr + x - 1];   // first X piles total
            if (alice == 1)
                res = Math.max(res, total + solve(alice == 1 ? 0 : 1, piles, curr + x, Math.max(M, x)));
            else res = Math.min(res, solve(alice == 1 ? 0 : 1, piles, curr + x, Math.max(M, x)));
        }

        return dp[alice][curr][M] = res;
    }
}