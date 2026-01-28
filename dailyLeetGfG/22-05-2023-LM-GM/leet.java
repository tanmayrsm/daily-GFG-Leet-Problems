class Solution {
    static int n;
    static boolean[][] vis;
    static int[][] d = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    static Queue<int[]> q;
    public int shortestBridge(int[][] grid) {
        n = grid.length;
        q = new LinkedList<>();
        vis = new boolean[n][n];
        boolean firstIslandFound = false;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++)
                if(grid[i][j] == 1 && !vis[i][j]) {
                    dfs(i, j, grid);
                    firstIslandFound = true;
                    break;
                }
            if(firstIslandFound)
                break;
        }

        int level = 0;
        while(!q.isEmpty()) {
            int s = q.size();
            for(int j = 0; j < s; j++) {
                int[] curr = q.poll();
                int x = curr[0];
                int y = curr[1];
                vis[x][y] = true;
                
                for(int i = 0; i < 4; i++) {
                    int xdx = x + d[i][0];
                    int ydy = y + d[i][1];
                    if(isValid(xdx, ydy, grid)) {
                        q.offer(new int[] {xdx, ydy});
                        vis[xdx][ydy] = true;
                        if(grid[xdx][ydy] == 1)
                            return level;
                    }
                }

            }
            level++;
        }
        return -1;
    }
    private static void dfs(int x, int y, int[][] grid) {
        if(!isValid(x, y, grid) || grid[x][y] == 0)
            return ;
        vis[x][y] = true;
        q.add(new int[] {x, y});
        dfs(x + 1, y, grid);
        dfs(x - 1, y, grid);
        dfs(x, y - 1, grid);
        dfs(x, y + 1, grid);
    }
    private static boolean isValid(int x, int y, int[][] grid) {
        if(x < 0 || y < 0 || x >= n || y >= n || vis[x][y])
            return false;
        return true;
    }
}