class Solution {    // reff
    public int numSquares(int n) {
        int dp[] = new int[n + 1];
        Arrays.fill(dp, n);
        dp[0] = 0;
        for(int i = 0; i < n + 1; i++) {
            for(int j = 1; j < i + 1; j++) {
                int square = j*j;
                if(square > i)  {break;}
                dp[i] = Math.min(dp[i], 1 + dp[i - square]);    // dp[8] = min(dp[8], 1 + dp[9 - 4])
            }
        }
        return dp[n];
    }
}