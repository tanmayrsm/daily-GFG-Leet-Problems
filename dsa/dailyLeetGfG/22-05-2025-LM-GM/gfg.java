
class Solution{
    static int[][] dp;
    static int minDeletions(String S) {
        //your code here
        int n = S.length();
        dp = new int[n][n];
        for(int[] x :  dp)
            Arrays.fill(x, -1);

        return n - solve(0, n - 1, S);
    }
    private static int solve(int l, int r, String S) {
        if(l > r) {
            return 0;
        }
        if(dp[l][r] != -1)  return dp[l][r];
        int takeL = Integer.MIN_VALUE, takeR = takeL, takeBoth = takeL, takeNone = takeL;
        if(S.charAt(l) == S.charAt(r)) {
            takeBoth = (l == r ? 1 : 2) + solve(l + 1, r - 1, S);
        }
        takeL = solve(l, r - 1, S);
        takeR = solve(l + 1, r, S);
        takeNone = solve(l + 1, r - 1, S);
        return dp[l][r] = Math.max(takeBoth, Math.max(takeL, Math.max(takeNone, takeR)));
    }
}