
class Solution
{
    //Function to find the length of longest common subsequence in two strings.
    static int lcs(int x, int y, String s1, String s2)
    {
        // your code here
        int n = s1.length(), n2 = s2.length();
        int ans = 0;
        int[][] dp = new int[n + 1][n2 + 1];
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n2; j++) {
                if(s1.charAt(i - 1) == s2.charAt(j - 1))
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                else    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                ans = Math.max(ans, dp[i][j]);
            }
        }
        return ans;
    }
    
}