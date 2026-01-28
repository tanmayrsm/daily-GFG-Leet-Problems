class Solution {
    private static int n;
    private static long[][][] dp;
    long numberOfPath(int N, int k, int [][]arr) {
        // code here
        n = N;
        dp = new long[n + 1][n + 1][k + 1];
        for(long[][] x : dp)
            for(long[] y : x)
                Arrays.fill(y, -1);
            
        if(arr[0][0] > k || arr[n - 1][n - 1] > k)  return 0;
        return solve(0, 0, arr, k - arr[0][0]);
    }
    private static long solve(int x, int y, int[][] arr, int k) {
        if(x == n - 1 && y == n - 1) {
            if(k == 0)  return 1L;
            return 0;
        }
        if(dp[x][y][k] != -1)  return dp[x][y][k];
        long goRight = 0, goDown = 0;
        if(isValid(x, y + 1) && k - arr[x][y + 1] >= 0) {
            goRight = solve(x, y + 1, arr, k - arr[x][y + 1]);
        }
        if(isValid(x + 1, y) && k - arr[x + 1][y] >= 0) {
            goDown = solve(x + 1, y, arr, k - arr[x + 1][y]);
        }
        return dp[x][y][k] = goDown + goRight;
    }
    private static boolean isValid(int x, int y) {
        return x >= 0 && y >= 0 && x < n && y < n;
    }
}