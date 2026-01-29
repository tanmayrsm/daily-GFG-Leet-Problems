class Solution {
    private static int[][] dp;
    private static int mod = 1000000007;
    public static int countWays(String s1, String s2) {
        // code here
        int n = s1.length(), m = s2.length();
        dp = new int[n + 1][m + 1];
        for(int[] x : dp)   Arrays.fill(x, -1);
        return solve(s1, s2, n, m, 0, 0);
    }
    private static int solve(String s1, String s2, int n, int m, int currN, int currM) {
        if(currM == m)  return 1;
        if(currN == n)  return 0;
        if (dp[currN][currM] != -1) return dp[currN][currM]; 
        int take = 0, nottake = 0;
        if(s1.charAt(currN) == s2.charAt(currM)) {
            take = solve(s1, s2, n, m, currN + 1, currM + 1);
        }
        nottake = solve(s1, s2, n, m, currN + 1, currM);
        return dp[currN][currM] = (take + nottake) % mod;
    }
}