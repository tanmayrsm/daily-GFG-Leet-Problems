
class Solution {    // reff
    public static int findWinner(int n, int x, int y) {
        // code here
        boolean[] dp = new boolean[n + 1];
        
        dp[0] = false;
        if(n >= 1) dp[1] = true;
        
        for(int i = 2 ; i <= n ; i++) {
            if(i - 1 >= 0 && !dp[i - 1]) {
                dp[i] = true;
            } else if (i - x >= 0 && !dp[i - x]) {
                dp[i] = true;
            } else if(i - y >= 0 && !dp[i - y]) {
                dp[i] = true;
            } else {
                dp[i] = false;
            }
        }
        
        return dp[n] ? 1 : 0;
    }
}