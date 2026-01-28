class Solution {
    public int[][] onesMinusZeros(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        int[] onesInRow = new int[n];
        int[] onesInCol = new int[m];
        
        // iterate for each rowSum
        for(int i = 0; i < n; i++) {
            int ones = 0;
            for(int j = 0; j < m; j++) {
                ones += grid[i][j];
            }
            onesInRow[i] = ones;
        }

        // iterate for each colSum
        for(int j = 0; j < m; j++) {
            int ones = 0;
            for(int i = 0; i < n; i++) {
                ones += grid[i][j];
            }
            onesInCol[j] = ones;
        }

        // final iteration
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++)
                grid[i][j] = onesInRow[i] + onesInCol[j] - ((n - onesInRow[i]) + (m - onesInCol[j]));
        }     

        return grid;   

        
    }
}