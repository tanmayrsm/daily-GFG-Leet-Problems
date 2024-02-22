
/*You are required to complete this method*/
class Solution
{
    private static int n, m, mod = 1000000007;
    private static long[][] dp;
    int subsequenceCount(String s, String t) {
	    // Your code here	
        n = s.length(); m = t.length();
        dp = new long[n + 1][m + 1];
        for(long[] x : dp)
            Arrays.fill(x, -1);
        return (int) solve(0, 0, s, t);
    }
    private static long solve(int x, int y, String s, String t) {
        if(y == m)  return 1L;
        if(x >= n)  return 0L;
        if(dp[x][y] != -1)  return dp[x][y];
        long take = 0, nottake = 0;
        if(s.charAt(x) == t.charAt(y)) {
            take = solve(x + 1, y + 1, s, t);
        }
        nottake = solve(x + 1, y, s, t);
        return dp[x][y] = (take + nottake) % mod;
    }
}