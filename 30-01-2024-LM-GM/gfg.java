class Solution 
{ 
    private static int n1, n2, n3;
    private static int[][][] dp;
    int LCSof3(String A, String B, String C, int N1, int N2, int N3) 
    { 
        // code here
        n1 = N1; n2 = N2; n3 = N3;
        dp = new int[n1][n2][n3];
        for(int[][] x : dp)
            for(int[] y : x)
                Arrays.fill(y, -1);

        return solve(0, 0, 0, A, B, C);
        
    }
    private static int solve(int c1, int c2, int c3, String A, String B, String C) {
        if(c1 == n1 || c2 == n2 || c3 == n3)    return 0;
        if(dp[c1][c2][c3] != -1)    return dp[c1][c2][c3];
        
        char p1 = A.charAt(c1), p2 = B.charAt(c2), p3 = C.charAt(c3);
        int takeAll = 0, takeSome = 0;
        if(p1 == p2 && p2 == p3) {
            takeAll = 1 + solve(c1 + 1, c2 + 1, c3 + 1, A, B, C);
        } else {
            int t1 = solve(c1 + 1, c2, c3, A, B, C);
            int t2 = solve(c1, c2 + 1, c3, A, B, C);
            int t3 = solve(c1, c2, c3 + 1, A, B, C);
            takeSome = Math.max(t1, Math.max(t2, t3));
        }
        return dp[c1][c2][c3] = Math.max(takeAll, takeSome);
    }
}