
class Solution {
    private static int m, n;
    private static int[][] dp;
    public int wildCard(String pattern, String str) {
        // Your code goes here
        m = pattern.length(); n = str.length();
        dp = new int[m + 1][n + 1];
        for (int[] x : dp)
            Arrays.fill(x, -1);
        int matches = isMatch(pattern, str, 0, 0);
        return matches == 1 ? 1 : 0;
    }
    private static int isMatch(String pattern, String str, int cP, int cS) {
        if (cS == n && cP == m)    return 1;
        if (cS >= n) {
            for (int i = cP; i < m; i++)
                if (pattern.charAt(i) != '*')
                    return 0;
            return 1;
        }
        if (cP == m)    return 0;
        if (dp[cP][cS] != -1) return dp[cP][cS];
        char p = pattern.charAt(cP), s = str.charAt(cS);
        int take = 0;
        if (p == s || p == '?') {
            take = isMatch(pattern, str, cP + 1, cS + 1);
        } else if (p == '*') {
            for (int i = cS; i < n; i++) {
                int goNext = isMatch(pattern, str, cP, cS + 1) ;
                int goBoth = isMatch(pattern, str, cP + 1, cS + 1) ;
                int dontConsider = isMatch(pattern, str, cP + 1, cS);
                if (goNext == 1 || goBoth == 1 || dontConsider == 1) {
                    take = 1;
                    break;
                }
            }
        }
        return dp[cP][cS] = take;
    }
}
