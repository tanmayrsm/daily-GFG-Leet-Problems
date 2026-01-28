class Solution {
    public int maxEqualRowsAfterFlips(int[][] matrix) {
        int n = matrix.length, m = matrix[0].length, ans = 0;
        int[] dp = new int[n];  

        // current equal no of rows/cols ->
        for(int i = 0; i < n; i++) {
            dp[i] = allElemSame(matrix[i]) ? 1 : 0;
            ans += dp[i];
        }
        ans = Math.max(ans, 1); // at least one row, u can make equal always

        // for each row, find its inverse row, and calculate dp[i]
        for(int i = n - 1; i >= 0; i--) {
            for(int j = i + 1; j < n; j++) {
                if (isEqual(matrix[i], matrix[j], matrix[i].length)) {
                    int ct = 0;
                    if(dp[j] == 0)
                        ct = 2;
                    else ct = dp[j] + 1;
                    dp[i] = Math.max(dp[i], ct);
                    ans = Math.max(ans, dp[i]);
                }
            }
        }

        return ans;
    }

    private boolean allElemSame(int[] x) {
        int n = x.length;
        for(int i = 1; i < n; i++) {
            if(x[i] != x[i - 1])  return false;
        } 
        return true;
    }

    private boolean isEqual(int[] x, int[] y, int n) {
        boolean eq = x[0] == y[0], neq = x[0] != y[0]; 
        for(int i = 0; i < n; i++) {
            if(x[i] == y[i] && neq)
                return false;
            else if (x[i] != y[i] && eq)
                return false;
        }
        return true;
    }
    // 10 => 000000 => 2^n
}