class Solution {    // referred soln
    Integer[][] dp;
    public int strangePrinter(String s) {
        int n = s.length();
        dp = new Integer[n][n];
        return solve(0, n-1, s);
    }

    public int solve(int i, int j, String s){
        if(i > j) return 0;
        if(dp[i][j] != null) return dp[i][j];
        int min = 1 + solve(i + 1, j, s);
        for(int k = i + 1; k <= j; k++){
            if(s.charAt(i) == s.charAt(k)){
                min = Math.min(min, solve(i, k - 1, s) + solve(k + 1, j, s));
            }
        }
        return dp[i][j] = min;
    }
}