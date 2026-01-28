
class Solution
{
    public int longestPalinSubseq(String S)
    {
        //code here
        char[] s = S.toCharArray();
        int n = s.length;
        int ans = 1;
        
        int[][] dp = new int[n][n];
        
        // since elements at diagonal r always same
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                if(i == j)
                    dp[i][j] = 1;
                    
        // start with substring of size 2
        for(int ct = 2; ct <= n; ct++) {
            for(int i = 0; i < n - ct + 1; i++) {   // window from i = 0 to ct; will move slide entire string
                int j = i + ct - 1; // character at extreme end, of this window
                if(s[i] == s[j])
                    dp[i][j] = 2 + dp[i + 1][j - 1];    // 2 + diagonally downward elem
                else    
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                
            }
        }
        
        return dp[0][n - 1];
                    
    }
    
}