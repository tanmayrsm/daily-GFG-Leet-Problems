class Solution {
    public int matrixScore(int[][] grid) {
        // aim - how to maximise binary at each row
        // then post this - maximise column binary for each
        int n = grid.length, m = grid[0].length, ans = 0;
        for(int i = 0; i < n; i++) {
            if(grid[i][0] == 0)
                flipRow(grid, i);
        }

        for(int j = 0; j < m; j++) {
            int oneCt = 0;
            for (int i = 0; i < n; i++)
                oneCt += (grid[i][j] == 1) ? 1 : 0;
            int zeroCt = n - oneCt;
            
            if (oneCt < zeroCt)
                flipCol(grid, j);
        }

        for(int i = 0; i < n; i++) {
            int total = 0;
            for(int j = 0; j < m; j++)
                if(grid[i][j] == 1)
                    total += Math.pow(2, m - 1 - j);
            
            ans += total;
        }

        return ans;

    }

    private static void flipRow(int[][] grid, int row) {
        for(int j = 0; j < grid[0].length; j++)
            grid[row][j] = (grid[row][j] == 1) ? 0 : 1;
    }

    private static void flipCol(int[][] grid, int col) {
        for(int i = 0; i < grid.length; i++)
            grid[i][col] = (grid[i][col] == 1) ? 0 : 1;
    }

    
}