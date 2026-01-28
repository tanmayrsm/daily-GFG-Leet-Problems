class Solution {
    private int[][] dp;
    private int[] a, b;
    private static final int NEG_INF = (int) -1e9;

    public int maxDotProduct(int[] nums1, int[] nums2) {
        a = nums1;
        b = nums2;
        int n = a.length, m = b.length;

        dp = new int[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], Integer.MIN_VALUE);
        }

        return solve(0, 0);
    }

    private int solve(int i, int j) {
        int n = a.length, m = b.length;
        if (i == n || j == m) return NEG_INF; // cannot form non-empty subsequence

        if (dp[i][j] != Integer.MIN_VALUE) return dp[i][j];

        int prod = a[i] * b[j];

        // 1) Take current pair: either start here, or extend further if beneficial
        int takeBoth = prod;
        int next = solve(i + 1, j + 1);
        if (next > 0) {
            takeBoth = prod + next;
        }

        // 2) Skip a[i]
        int skipA = solve(i + 1, j);

        // 3) Skip b[j]
        int skipB = solve(i, j + 1);

        return dp[i][j] = Math.max(takeBoth, Math.max(skipA, skipB));
    }
}
