class Solution {
    private static int[][] dir = new int[][] {{-2,-1},{-2,1},{-1,-2},{-1,2}, {2,-1},{2,1},{1,-2},{1,2}};
    private static int ans;
    private static int mod = 1000000007;
    private static boolean[][] vis;
    private static long[][][] dp;
    public int knightDialer(int n) {
        ans = 0;
        vis = new boolean[4][3];
        vis[3][0] = true; vis[3][2] = true; // * and # keys
        dp = new long[n][4][3];
        for(long[][] x : dp)
            for(long[] y : x)
                Arrays.fill(y, -1);

        long ct = 0;
        for(int i = 0; i < vis.length; i++)
            for(int j = 0; j < vis[i].length; j++)
                if(!vis[i][j])
                    ct += solve(i, j, n - 1);
        return (int)(ct%mod);
    }
    private static long solve(int x, int y, int n) {
        if(n == 0) {
            return 1;
        }  
        if(dp[n][x][y] != -1)   return dp[n][x][y];

        long ct = 0;
        for(int[] d : dir) {
            int xx = x + d[0];
            int yy = y + d[1];
            if(isValid(xx, yy))
                ct += solve(xx, yy, n - 1);
        }
        return dp[n][x][y] = ct % mod;
    }
    private static boolean isValid(int x, int y) {
        if(x < 0 || y < 0 || x >= vis.length || y >= vis[0].length || vis[x][y])    return false;
        return true;
    }
}