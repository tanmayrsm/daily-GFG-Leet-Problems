class Solution {
    public int countUnguarded(int m, int n, int[][] guards, int[][] walls) {
        int[][] grid = new int[m][n];
        int ans = 0;
        for (int[] g : guards) {
            grid[g[0]][g[1]] = 2;
        }
        for (int[] w : walls) {
            grid[w[0]][w[1]] = -1;
        }
        for (int[] g : guards) {
            int[] pos = g;
            int x = pos[0], y = pos[1], X = x + 1, Y = y + 1;
            grid[x][y] = 3;
            // right dir
            while (Y < n && (grid[x][Y] == 0 || grid[x][Y] == 3)) {
                grid[x][Y++] = 3;
            }
            Y = y - 1;
            // left dir
            while (Y >= 0 && (grid[x][Y] == 0 || grid[x][Y] == 3)) {
                grid[x][Y--] = 3;
            }
            Y = y;
            while (X < m && (grid[X][y] == 0 || grid[X][y] == 3)) {
                grid[X++][y] = 3;
            }
            X = x - 1;
            while (X >= 0 && (grid[X][y] == 0 || grid[X][y] == 3)) {
                grid[X--][y] = 3;
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // System.out.print(grid[i][j] + "::");
                if (grid[i][j] == 0) ans++;
            }
            System.out.println();
        }

        return ans;
    }
}