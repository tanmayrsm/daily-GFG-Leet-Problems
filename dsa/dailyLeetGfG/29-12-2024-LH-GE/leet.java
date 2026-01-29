class Solution {
    public int numWays(String[] words, String target) {
        int alphabet = 26;
        int mod = 1000000007;
        int n = target.length(), k = words[0].length();
        int cnt[][] = new int[alphabet][k];
        for (int j = 0; j < k; j++) {
            for (String word : words) {
                cnt[word.charAt(j) - 'a'][j]++;
            }
        }
        long dp[][] = new long[n + 1][k + 1];
        dp[0][0] = 1;
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j < k; j++) {
                if (i < n) {
                    dp[i + 1][j + 1] += cnt[target.charAt(i) - 'a'][j] * dp[i][j];
                    dp[i + 1][j + 1] %= mod;
                }
                dp[i][j + 1] += dp[i][j];
                dp[i][j + 1] %= mod;
            }
        }
        return (int)dp[n][k];
    }
    
}