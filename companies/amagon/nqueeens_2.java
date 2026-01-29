class Solution {
    int ans;
    public int totalNQueens(int n) {
        int[][] grid = new int[n][n];
        ans = 0;
        
    }
    private boolean place(int[][] grid, int currRow) {
        if(currRow == grid.length) {
            ans++;
            return true;
        }
        for(int j = 0; j < grid[0].length; j++) {
            if (check(currRow, j, grid)) {
                grid[currRow][j] = 1;
                if(place(grid, currRow + 1)) return true;
                grid[currRow][j] = 0;
            }
        }
        return false;
    }
    private boolean check(int row, int col, int[][] grid) {
        for(int i = 0; i < row; i++)
            if(grid[i][col] == 1)
                return false;
        int cCol = col - 1;
        for(int i = row - 1; i >= 0 && cCol >= 0; i--) {
            if(grid[i][cCol] == 1)  return false;
            else cCol--;
        }
        cCol = col - 1;
        for(int i = row - 1; i >= 0 && cCol < grid[0].length; i--) {
            if(grid[i][cCol] == 1)  return false;
            else cCol++;
        }
        return true;
    }
}