class Solution {
    int[][] dirs = new int[][]{{-1, 1}, {1, 1}, {1, -1}, {-1, -1}};
    int[][] grid;
    int m, n;
    
    public int lenOfVDiagonal(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        this.grid = grid;
        
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    res = Math.max(res, 1);
                    for (int d = 0; d < 4; d++) {
                        res = Math.max(res, dfs(i, j, d, 2, false));
                    }
                }
            }
        }
        return res;
    }

    private int dfs(int i, int j, int dir, int target, boolean turned) {
        int x = i + dirs[dir][0], y = j + dirs[dir][1];
        if (x < 0 || x >= m || y < 0 || y >= n || grid[x][y] != target) {
            return 1;
        }

        int straight = 1 + dfs(x, y, dir, target == 2 ? 0 : 2, turned);
        int turn = 0;
        if (!turned) {
            turn = 1 + dfs(x, y, (dir + 1) % 4, target == 2 ? 0 : 2, true);
        }
        return Math.max(straight, turn);
    }
}