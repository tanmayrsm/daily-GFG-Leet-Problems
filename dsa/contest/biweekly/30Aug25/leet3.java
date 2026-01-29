class Solution {
    int n, m, mod = 1000000007;
    boolean[][] vis;
    public int uniquePaths(int[][] grid) {
        n = grid.length; m = grid[0].length;
        vis = new boolean[n][m];
        return (int)solve(grid, 0, 0);
    }
    private long solve(int[][] grid, int x, int y) {
        if (x == n - 1 && y == m - 1)   return 1;
        // go right
        long right = 0, down = 0;
        if (grid[x][y] < 0) {   // means someone is trying to come at it from left->right, so push him down
            if (isValid(x + 1, y)) {
                vis[x + 1][y] = true;
                down = solve(grid, x + 1, y);
                vis[x + 1][y] = false;
            }
        } else if (grid[x][y] > 0) {    // someone trying to come at it from top
            if (isValid(x, y + 1)) {
                if (grid[x][y + 1] == 1)    grid[x][y + 1] = -1;
                vis[x][y + 1] = true;
                right = solve(grid, x, y + 1);
                grid[x][y + 1] = 1;
                vis[x][y + 1] = false;
            }
        } else {
            if (isValid(x, y + 1)) {
                if (grid[x][y + 1] == 1)    grid[x][y + 1] = -1;
                vis[x][y + 1] = true;
                right = solve(grid, x, y + 1);
                grid[x][y + 1] = 1;
                vis[x][y + 1] = false;
            }
            if (isValid(x + 1, y)) {
                vis[x + 1][y] = true;
                down = solve(grid, x + 1, y);
                vis[x + 1][y] = false;
            }
        }
        long res = (right + down) % mod;
        vis[x][y] = false;
        return res;
    }
    private boolean isValid(int x, int y) {
        if (x < 0 || y < 0 || x >= n || y >= m || vis[x][y]) return false;
        return true;
    }
}