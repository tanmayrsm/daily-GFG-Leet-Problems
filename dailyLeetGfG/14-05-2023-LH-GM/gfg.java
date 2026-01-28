
class Solution {

    public static long findMaxSubsetSum(int N, int[] A) {
        // code here
        long[][] dp = new long[N+1][2];
        for(int i = 1; i < N + 1; i++) {
            long x = 0;
            long y = 0;
            x = A[i-1] + dp[i-1][0];    // 0 index denotes, I take curr elem
            y = dp[i-1][1];             // 1 denotes, Im not  taking curr elem
            dp[i][1] = x;   // taking curr elem
            dp[i][0] = Math.max(x, y);  // taking or not taking current ka max
        }
        return dp[N][0];
    }
}
        
