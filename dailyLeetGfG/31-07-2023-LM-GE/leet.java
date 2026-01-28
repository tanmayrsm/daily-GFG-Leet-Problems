class Solution {
    private static int[][] dp ;
    public int minimumDeleteSum(String s1, String s2) {
        int n = s1.length(), m = s2.length();
        dp = new int[n + 1][m + 1];
        return helper(s1, s2, n - 1, m - 1);
    }
    private static int helper(String s1, String s2, int i, int j) {
        int s = 0;
        if(i < 0) {
            for(int k = 0; k <= j; k++)
                s += (int)s2.charAt(k);
            return s;
        }
        
        if(j < 0) {
            for(int k = 0; k <= i; k++)
                s += (int)s1.charAt(k);
            return s;
        }

        if(dp[i][j] != 0)   return dp[i][j];
        if(s1.charAt(i) == s2.charAt(j))    
            return dp[i][j] = helper(s1, s2, i - 1, j - 1);
        int n1 = (int)s1.charAt(i) + helper(s1, s2, i - 1, j);
        int n2 = (int)s2.charAt(j) + helper(s1, s2, i, j - 1);
        return dp[i][j] = Math.min(n1, n2);
    }
}