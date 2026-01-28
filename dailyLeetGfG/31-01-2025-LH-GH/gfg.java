class Solution {
    
    //Function to find a solved Sudoku. 
    static boolean solveSudoku(int grid[][])
    {
        // add your code here
        for(int i = 0; i < 9; i++)
            for(int j = 0; j < 9; j++) {
                if(grid[i][j] == 0) {
                    for(int val = 1; val <= 9; val++) {
                        if(isValid(grid, i, j, val)) {  // can I insett val? at i, j?
                            grid[i][j] = val;
                            if(solveSudoku(grid))
                                return true;
                            else grid[i][j] = 0;
                        }
                    }
                    return false;
                }
            }
        return true;
            
    }
    
    private static boolean isValid(int[][] grid, int r, int c, int val) {
        for(int i = 0; i < 9; i++) {
            // check if - value already in same row/same col of entire 9x9 box
            if(grid[i][c] == val || grid[r][i] == val)  return false;   
            
            // below complicated condition, checks if all 9 cells in one box dont contain same val
            if(grid[3 * (r/3) + i / 3][3 * (c / 3) + i % 3] ==val)  return false;
        }
        return true;
    }
    
    //Function to print grids of the Sudoku.
    static void printGrid (int grid[][])
    {
        // add your code here
        for(int i = 0; i < 9; i++)
            for(int j = 0; j < 9; j++)
                System.out.print(grid[i][j] + " ");
    }
}