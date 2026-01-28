
class Solution {
    int distinctSubsequences(String S) {
        // code here
        // PURE DP, dont think of making recursion
        // current state depends on previous state, with an observation of repeated characters
        
        int n = S.length();
        int[] prevIndexOfChar = new int[26];
        Arrays.fill(prevIndexOfChar, -1);
        int[] dp = new int[n];
        int mod = 1000000007;
        
        dp[0] = 2;
        prevIndexOfChar[S.charAt(0) - 'a'] = 0;
        
        for(int i = 1; i < n; i++) {
            dp[i] = (2 * dp[i - 1]) % mod;
            
            int indo = S.charAt(i) - 'a';
            if(prevIndexOfChar[indo] != -1) {
                // as im not storing dp[0] = 1, dp[1] = 2
                // but storing dp[0] = 2 directly, hence adding if-else
                if(prevIndexOfChar[indo] - 1 < 0) {
                    dp[i] = dp[i] - 1;
                } else
                    dp[i] = (dp[i] - dp[prevIndexOfChar[indo] - 1] + mod) % mod;
            }
            
            prevIndexOfChar[indo] = i;
        }
        
        return dp[n - 1];
    }
}