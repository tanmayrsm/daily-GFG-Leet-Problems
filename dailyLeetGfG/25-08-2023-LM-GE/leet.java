class Solution {
    static int[][][] dp;
    public boolean isInterleave(String s1, String s2, String s3) {
        int len1 = s1.length(), len2 = s2.length(), len3 = s3.length();
        dp = new int[101][101][201];
        for(int x[][] : dp)
            for(int y[] : x)
                Arrays.fill(y, -1);

        if(len1 + len2 != len3) return false;
        return solve(s1, s2, s3, 0, 0, 0) == 1 ? true : false;
    }

    private static int solve(String s1, String s2, String s3, int i, int j, int k) {
        if(k == s3.length())
            return 1;
        if(i < s1.length() && j < s2.length() && k < s3.length() && dp[i][j][k] != -1)
            return dp[i][j][k];

        int takes1 = 0, takes2 = 0;
        if(i < s1.length() && s3.charAt(k) == s1.charAt(i))
            takes1 = solve(s1, s2, s3, i + 1, j, k + 1);

        if(j < s2.length() && s2.charAt(j) == s3.charAt(k))
            takes2 = solve(s1, s2, s3, i, j + 1, k + 1);
        return dp[i][j][k] = takes1 == 1 || takes2 == 1 ? 1 : 0;
    }
}