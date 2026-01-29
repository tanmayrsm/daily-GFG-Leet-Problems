class Solution {
    private static int[][][] dp;
    private static int[][] dir = new int[][] {{0,-1},{0,1},{1,0},{-1,0}};
    private static int M, N, MAX = Integer.MAX_VALUE, mod = 1000000007;
    public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        dp = new int[m + 1][n + 1][maxMove + 1];
        for(int[][] x : dp)
            for(int[] y : x)
                Arrays.fill(y, -1);
        M = m; N = n;
        return solve(startRow, startColumn, maxMove);
    }
    private static int solve(int currX, int currY, int currMove) {
        if(currMove < 0)    return MAX;
        if(currX < 0 || currY < 0 || currX >= M || currY >= N)    return 1;
        if(dp[currX][currY][currMove] != -1)  return dp[currX][currY][currMove];
        long noOFMoves = 0;
        for(int x[] : dir) {
            int newX = x[0], newY = x[1];
            int r = solve(currX + newX, currY + newY, currMove - 1);
            if(r != MAX)
                noOFMoves += r;
        }
        return dp[currX][currY][currMove] = (int)(noOFMoves % mod);
    }
}