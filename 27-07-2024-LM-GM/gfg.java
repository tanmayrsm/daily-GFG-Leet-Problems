
//User function Template for Java
class Solution {    // reff
    static int countMin(String str) {
        int n = str.length();
        int[][] dp = new int[n][n];
        
        // Initialize the diagonal elements (single character palindromes)
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }
        
        // Fill the remaining elements of the dp array
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i < n - len + 1; i++) {
                int j = i + len - 1;
                
                // If characters at i and j are the same
                if (str.charAt(i) == str.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } 
                // If characters at i and j are different
                else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        
        // The minimum number of deletions required
        return n - dp[0][n - 1];
    }
}
