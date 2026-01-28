class Solution {
    static int[] dp;
    public int integerBreak(int n) {
        if(n == 2 || n == 3)    return n - 1;
        dp = new int[n + 1];
        Arrays.fill(dp, -1);
        return solve(n);
    }
    private static int solve(int n) {
        if(n <= 0)  return 1;
        if(dp[n] != -1) return dp[n];
        int something = 1, maxi = 1;
        for(int i = 1; i <= n; i++) {
            something = i * solve(n - i);
            maxi = Math.max(maxi, something);
        }  
        return dp[n] = maxi;
    }
}