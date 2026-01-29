class Solution {
    int n, m, MAX = Integer.MAX_VALUE;
    public int minCost(int[][] grid, int k) {
        n = grid.length; m = grid[0].length;
        return solve(grid, 0, 0, k);
    }
    private int solve(int[][] grid, int x, int y, int k) {
        if (x == n - 1 && y == m - 1)   return 0;
        int right = MAX, down = MAX, teleport = MAX;
        if (y + 1 < m && grid[x][y] < grid[x][y + 1])
            right = grid[x][y] + solve(grid, x, y + 1, k);
        if (x + 1 < n grid[x][y] < grid[x + 1][y])
            down = grid[x][y] + solve(grid, x + 1, y, k);
        if (k > 0) {
            for (int i = x; i < n; i++) {
                for (int j = y + 1; j < m; j++) {
                    teleport = Math.min(teleport, solve(i, j, k-1));
                }
            }
        }
        return Math.min(right, Math.min(down, teleport));
    }
}