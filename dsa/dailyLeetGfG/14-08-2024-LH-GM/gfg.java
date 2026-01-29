
// User function Template for Java

class Solution {
    public int longestCommonSubstr(String S1, String S2) {
        // code here
        char[] ch1 = S1.toCharArray();
        char[] ch2 = S2.toCharArray();
        int ans = 0, n = S1.length(), m = S2.length();
        
        int[][] dp = new int[n + 1][m + 1];
        for(int i = 1; i < n + 1; i++) {
            for(int j = 1; j < m + 1; j++) {
                if(ch1[i - 1] == ch2[j - 1]) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 0;   // changed line in LCS algo
                }
                ans = Math.max(ans, dp[i][j]);
            }
        }
        return ans;
    }
}