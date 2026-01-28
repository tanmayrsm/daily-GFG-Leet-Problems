
class Solution {

    static boolean[][] vis;
    static int n, m;
    static int[][] d = new int[][] {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    int numberOfEnclaves(int[][] grid) {
        // Your code here
        n = grid.length; m = grid[0].length;
        // many edge cases are present for n == 1 || m == 1, hence covering them here
        // IN CASE OF SPIRAL PRINT, PUT 2 for loops, to track each
        if(n == 1 || m == 1) {
            return 0;
        }
    
        vis = new boolean[n][m];
        int ans = 0;


        // only one for loop to track the boundaries - spirally once RIGHT-> BOTTOM -> LEFT -> TOP
        int l = 0, r = 0;
        String dir ="RIGHT";
        do {
            switch (dir) {
                case "RIGHT" : {
                    while(r != m - 1) {
                        if(grid[l][r] == 1)
                            dfs(grid, l, r);
                        r++;
                    }
                    dir = "BOTTOM";
                    break;
                }
                case "BOTTOM" : {
                    while(l != n - 1) {
                        if(grid[l][r] == 1)
                            dfs(grid, l, r);
                        l++;
                    }
                    dir = "LEFT";
                    break;
                }
                case "LEFT" : {
                    while(r != 0) {
                        if(grid[l][r] == 1)
                            dfs(grid, l, r);
                        r--;
                    }
                    dir = "TOP";
                    break;
                }
                case "TOP" : {
                    while(l != 0) {
                        if(grid[l][r] == 1)
                            dfs(grid, l, r);
                        l--;
                    }
                    dir = "RIGHT";
                    break;
                }
                default : break;
            }
        } while(l != 0 || r != 0);


        for(int i = 0; i < n; i++)
            for(int j = 0; j < m; j++)
                if(grid[i][j] == 1)
                    ans++;

        return ans;
        
    }
    private static void dfs(int[][] grid, int x, int y) {
        grid[x][y] = 2;
        vis[x][y] = true;
        for(int[] dir : d) {
            int xx = x + dir[0], yy = y + dir[1];
            if(isValid(xx, yy, grid) && grid[xx][yy] == 1) {
                dfs(grid, xx, yy);
            }
        }
    }
    private static boolean isValid(int x, int y, int[][] grid) {
        if(x < 0 || y < 0 || x >= n || y >= m || vis[x][y])  return false;
        return true;
    }}