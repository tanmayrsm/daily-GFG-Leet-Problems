class Solution {
    private static int MAX = Integer.MAX_VALUE;
    private static int[][] dp;
    public int editDistance(String str1, String str2) {
        // Code here
        int n = str1.length(), m = str2.length();
        dp = new int[n][m];
        for(int[] d : dp)
            Arrays.fill(d, -1);
        return solve(str1, str2, 0, 0, n, m);
    }
    private static int solve (String s1, String s2, int c1, int c2, int n, int m) {
        int remCurrLen = n - 1 - c1, remReqLen = m - 1 - c2;
        if (c1 == n)    return Math.max(0, remReqLen + 1);
        if (c2 == m)    return Math.max(0, remCurrLen + 1);
        
        if (dp[c1][c2] != -1)
            return dp[c1][c2];

        char char1 = s1.charAt(c1), char2 = s2.charAt(c2);
        int doNothing = MAX, insert = MAX, delete = MAX, replace = MAX;
        if (char1 == char2) {
            doNothing = solve(s1, s2, c1 + 1, c2 + 1, n, m);
        } else {
            insert = 1 + solve(s1, s2, c1, c2 + 1, n, m);
            replace = 1 + solve(s1, s2, c1 + 1, c2 + 1, n, m);
            delete = 1 + solve(s1, s2, c1 + 1, c2, n, m);
        }

        return dp[c1][c2] = Math.min(doNothing, Math.min(insert, Math.min(delete, replace)));
    }
}