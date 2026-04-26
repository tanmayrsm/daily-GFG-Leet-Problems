class Solution {
    int[][] dir = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public boolean containsCycle(char[][] grid) {
        int n = grid.length, m = grid[0].length;
        boolean[][] vis = new boolean[n][m];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                // keep your names, just add !vis[i][j]
                if(!vis[i][j] && solve(grid, vis, n, m, i, j, i, j))  return true;
            }
        }
        return false;
    }

    // keep name and parameters, change body
    private boolean solve(char[][] grid, boolean[][] vis, int n, int m, int x1, int y1, int x2, int y2) {
        Queue<int[]> q = new LinkedList<>();   // {x, y, px, py}
        q.offer(new int[] {x1, y1, -1, -1});
        vis[x1][y1] = true;
        char curr = grid[x1][y1];

        while (!q.isEmpty()) {
            int[] p = q.poll();
            int x = p[0], y = p[1];
            int px = p[2], py = p[3];

            for (int[] d : dir) {
                int xx = x + d[0], yy = y + d[1];
                if (xx < 0 || yy < 0 || xx >= n || yy >= m) continue;
                if (grid[xx][yy] != curr) continue;

                // skip the parent
                if (xx == px && yy == py) continue;

                // visited neighbor with same char and not parent → cycle
                if (vis[xx][yy]) return true;

                vis[xx][yy] = true;
                q.offer(new int[] {xx, yy, x, y});
            }
        }
        return false;
    }
}