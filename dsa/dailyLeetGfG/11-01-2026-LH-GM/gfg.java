class Solution {
    private static final int INF = (int)1e9;
    int[][] dp;
    String s1, s2;

    // DERIVED TABULATION FROM MEMOIZATION
    public String minWindow(String s1, String s2) {
        int n = s1.length(), m = s2.length();
        if (m == 0) return "";
        if (n == 0 || m > n) return "";

        // dp[c1][c2] = minimal length of window in s1 starting at c1
        // that contains s2[c2..] as subsequence, or INF if impossible
        int[][] dp = new int[n + 1][m + 1];

        // Initialize base cases:
        // dp[n][c2] = INF for all c2 < m (no chars left but still need to match)
        for (int c2 = 0; c2 < m; c2++) {
            dp[n][c2] = INF;
        }
        // dp[c1][m] = 0 for all c1 (matching empty suffix of s2)
        for (int c1 = 0; c1 <= n; c1++) {
            dp[c1][m] = 0;
        }

        // Fill table bottom-up
        for (int c1 = n - 1; c1 >= 0; c1--) {
            for (int c2 = m - 1; c2 >= 0; c2--) {
                int res;

                if (s1.charAt(c1) == s2.charAt(c2)) {
                    // take
                    int take = dp[c1 + 1][c2 + 1];
                    if (take < INF) take += 1;

                    // skip
                    int skip = dp[c1 + 1][c2];
                    if (skip < INF) skip += 1;

                    res = Math.min(take, skip);
                } else {
                    // only skip
                    int skip = dp[c1 + 1][c2];
                    if (skip < INF) skip += 1;
                    res = skip;
                }

                dp[c1][c2] = res;
            }
        }

        // dp[c1][0] now holds minimal length of window starting at c1
        // that covers full s2
        int bestLen = INF;
        int bestStart = -1;
        for (int c1 = 0; c1 < n; c1++) {
            int len = dp[c1][0];
            if (len < bestLen) {
                bestLen = len;
                bestStart = c1;
            }
        }

        if (bestStart == -1 || bestLen >= INF) return "";
        return s1.substring(bestStart, bestStart + bestLen);
    }

    // MEMOIZATION solution -
    // public String minWindow(String s1, String s2) {
    //     this.s1 = s1;
    //     this.s2 = s2;
    //     int n = s1.length(), m = s2.length();

    //     // edge cases
    //     if (m == 0) return "";
    //     if (n == 0 || m > n) return "";

    //     dp = new int[n][m];
    //     for (int i = 0; i < n; i++) {
    //         Arrays.fill(dp[i], -1);
    //     }

    //     int bestLen = INF;
    //     int bestStart = -1;

    //     // try every possible start index in s1
    //     for (int i = 0; i < n; i++) {
    //         int len = solve(i, 0);   // minimal length of window starting at i that covers all of s2
    //         if (len < bestLen) {
    //             bestLen = len;
    //             bestStart = i;
    //         }
    //     }

    //     if (bestStart == -1 || bestLen >= INF) return "";
    //     return s1.substring(bestStart, bestStart + bestLen);
    // }

    // // returns minimal length of window in s1 starting at c1 that contains s2[c2..] as subsequence
    // // or INF if impossible
    // private int solve(int c1, int c2) {
    //     int n = s1.length(), m = s2.length();

    //     if (c2 == m) return 0;          // already matched all of s2
    //     if (c1 == n) return INF;        // no chars left in s1 but s2 not done

    //     if (dp[c1][c2] != -1) return dp[c1][c2];

    //     int res = INF, take = INF;
    //     if (s1.charAt(c1) == s2.charAt(c2)) {
    //         // option 1: take this char as part of window
    //         take = solve(c1 + 1, c2 + 1);
    //         if (take < INF) take += 1;  // count current char
    //         res = Math.min(res, take);

    //     }
    //     // always available: skip current s1[c1]
    //     int skip = solve(c1 + 1, c2);
    //     if (skip < INF) skip += 1;
    //     res = Math.min(res, skip);

    //     return dp[c1][c2] = res;
    // }
}
