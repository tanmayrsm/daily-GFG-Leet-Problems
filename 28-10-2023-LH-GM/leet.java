class Solution {
    long[][] dp;
    int mod = 1000000007;

    public int countVowelPermutation(int n) {
        dp = new long[n][26];
        for(long[] x : dp)
            Arrays.fill(x, -1);

        return (int)(solve(n, 0, 'z') % mod);
    }

    private long solve(int n, int curr, char last) {
        if(curr == n) return 1;

        if(dp[curr][last - 'a'] != -1)  return dp[curr][last - 'a'];
        
        long takeA = 0, takeE = 0, takeI = 0, takeO = 0, takeU = 0, takeAll = 0;
        
        if(last == 'a')
            takeA = solve(n, curr + 1, 'e');
        if(last == 'e') {
            takeE = solve(n, curr + 1, 'a') + solve(n, curr + 1, 'i');
        }
        if(last == 'i') {
            takeI = solve(n, curr + 1, 'a') + solve(n, curr + 1, 'e') + solve(n, curr + 1, 'o') + solve(n, curr + 1, 'u');
        }
        if(last == 'o') {
            takeO = solve(n, curr + 1, 'i') + solve(n, curr + 1, 'u');
        }
        if(last == 'u') 
            takeU = solve(n, curr + 1, 'a');

        if(last == 'z') {
            takeAll = solve(n, curr + 1, 'a') + solve(n, curr + 1, 'e') + solve(n, curr + 1, 'i') + solve(n, curr + 1, 'o') + solve(n, curr + 1, 'u');
        }
        long rem = (takeA + takeE + takeI + takeO + takeU + takeAll) % mod;
        return dp[curr][last - 'a'] = rem ;
    }
}