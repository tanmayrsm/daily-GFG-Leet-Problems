
class Solution {    // reff
    public long maxPoints(int[][] points) {
        long[] dp = new long[points[0].length];
        
        for (int i = 0; i < points.length; i ++) {
            for (int j = 0; j < points[0].length; j ++) {
                dp[j] += points[i][j];
            }
            
            for (int j = 1; j < dp.length; j ++) { 
                dp[j] = Math.max(dp[j], dp[j - 1] - 1);
            }
            
            for (int j = dp.length - 2; j >= 0; j --) {
                dp[j] = Math.max(dp[j], dp[j + 1] - 1);
            }
        }
        
        long result = 0;
        for (int i = 0; i < dp.length; i ++) {
            result = Math.max(result, dp[i]);
        }
        
        return result;
    }
}