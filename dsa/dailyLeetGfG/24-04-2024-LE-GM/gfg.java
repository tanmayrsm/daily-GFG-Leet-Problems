import java.util.Arrays;

class Solution
{
    private static int mod = 1000000007;
    private static int[][] dp;
    public static int ways(int n, int m)
    {
        // complete the function
        dp = new int[n + 1][m + 1];
        for(int[] x : dp)   Arrays.fill(x, -1);
        return solve(n, m);
    }

    private static int solve(int x, int y) {
        if(x == 0 && y == 0)    return 1;
        if(dp[x][y] != -1)  return dp[x][y];
        int left = 0, right = 0;
        if(x > 0)
            left = solve(x - 1, y) % mod;
        if(y > 0)   
            right = solve(x, y - 1) % mod;
        return dp[x][y] = (left + right) % mod;
    }
}