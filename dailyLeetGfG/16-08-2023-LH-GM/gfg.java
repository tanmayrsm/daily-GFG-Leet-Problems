class Solution {
    public static int findCatalan(int n) {
        // code here
        int[] dp = new int[n + 1];
        int mod = 1000000007;
        dp[0] = 1;
        dp[1] = 1;
        for(int i = 2; i <= n; i++) {
            long ans = 0;
            for(int j = 0; j < i; j++) {
                long a = dp[j];
                long b = dp[i - 1 - j];
                ans = ans + (a * b);
                ans = ans % mod;
            }
            dp[i] = (int)ans%mod;
        }
        return dp[n];
    }
}
        