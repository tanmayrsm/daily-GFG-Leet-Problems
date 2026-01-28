
class Solution {
    // Function to find the number of islands.
    private int[][] dir = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {1,1}, {-1, -1}, {-1, 1}, {1, -1}};
    public int numIslands(char[][] grid) {
        // Code here
        int ans = 0;
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == '1') {
                    ans++;
                    solve(grid, i, j);
                }
            }
        }
        return ans;
    }
    
    private void solve(char[][] grid, int x, int y) {
        for(int[] d : dir) {
            int xx = d[0] + x, yy = d[1] + y;
            if(xx >= 0 && yy >= 0 && xx < grid.length && yy < grid[0].length && grid[xx][yy] == '1') {
                grid[xx][yy] = '0';
                solve(grid, xx, yy);
            }
        }        
    }
}