
class Solution
{
    static int[][] dp;
    //Function to find length of shortest common supersequence of two strings.
    public static int shortestCommonSupersequence(String s,String t,int m,int n)
    {
        //Your code here
        dp = new int[100][100];
        for(int[] x : dp)
            Arrays.fill(x, -1);
        return (n + m) - solve(m - 1, n - 1, s, t);
    }
    
    
    private static int solve(int i, int j, String s, String t)
    {
        if(i < 0 || j < 0) return 0;
        
        if(dp[i][j] != -1) return dp[i][j];
        
        if(s.charAt(i) == t.charAt(j)) return dp[i][j] = 1 + solve(i-1, j-1, s, t);
        else
        {
            int x = solve(i-1, j, s, t);
            int y = solve(i, j-1, s, t);
            
            return dp[i][j] = Math.max(x, y);
        }
    }
}