class Solution {
    int MOD = 1_000_000_007;
    long[][] dp;
    public int countCoprime(int[][] mat) {
        int n = mat.length, m = mat[0].length;
        dp = new long[mat.length + 1][151];
        long ans = 0;
        for (long[] d : dp) Arrays.fill(d, -1);
        for(int j = 0; j < mat[0].length; j++) {
            ans += solve(mat, 1, mat[0][j]);
            ans %= MOD;
        }
        return (int)ans;
    }
    private long solve(int[][] mat, int row, int gcd) {
        if(row == mat.length) {
            return (gcd == 1) ? 1 : 0;
        }
        if(dp[row][gcd] != -1)
            return dp[row][gcd];
        long ans = 0;
        for (int i = 0; i < mat[0].length; i++) {
            int currGCD = gcd(gcd, mat[row][i]);
            // System.out.println("gcd ::" + gcd + "::" + mat[row][i] + "::" + currGCD);
//            if(currGCD  1) {
            long res = solve(mat, row + 1, currGCD);
            ans += (res % MOD);
            ans %= MOD;
        }
        return dp[row][gcd] = ans;
    }
    private int gcd(int a, int b) {
        if(b < a)   return gcd(b, a);
        if(a == 0)  return b;
        return gcd(a, b % a);
    }
}