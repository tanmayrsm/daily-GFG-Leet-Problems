class Solution {
    public boolean canPartitionGrid(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        long prefRowSum = 0, prefColSum = 0, rowSum = 0, colSum = 0;
        long[] prefRow = new long[n];
        long[] prefCol = new long[m];

        for(int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                prefRow[i] += grid[i][j];
                prefCol[j] += grid[i][j];
            }
        }
        for(int i = 0; i < n; i++)  rowSum += prefRow[i];
        for(int i = 0; i < m; i++)  colSum += prefCol[i];

        for(int i = 0; i < n; i++) {
            prefRowSum += prefRow[i];
            if(prefRowSum == rowSum - prefRowSum)
                return true;
        }

        for(int i = 0; i < m; i++) {
            prefColSum += prefCol[i];
            if(prefColSum == colSum - prefColSum)
                return true;
        }

        return false;
    }
}
