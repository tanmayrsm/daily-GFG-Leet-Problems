class Solution {
    int[][][] dp;
    int n, m;
    public int numberOfPath(int[][] mat, int k) {
        // code here
        n = mat.length; m = mat[0].length;
        dp = new int[n][m][201];
        for (int[][] y : dp)
            for(int[] x : y)
                Arrays.fill(x, -1);
        return solve(mat, 0, 0, mat[0][0], k);
    }
    private int solve(int[][] mat, int i, int j, int collected, int k) {
        if (i == n - 1 && j == m - 1) {
            if (collected == k) return 1;
            return 0;
        }
        if (dp[i][j][collected] != -1) return dp[i][j][collected];
        if (collected > k)  return 0;
        int takeDown = 0, takeRight = 0;
        if (i + 1 < n) {
            takeDown = solve(mat, i + 1, j, collected + mat[i + 1][j], k);
        }
        if (j + 1 < m) {
            takeRight = solve(mat, i, j + 1, collected + mat[i][j + 1], k);
        }
        return dp[i][j][collected] = takeDown + takeRight;
    }
}