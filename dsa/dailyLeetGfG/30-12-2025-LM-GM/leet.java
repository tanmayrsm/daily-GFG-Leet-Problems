class Solution {
    public int numMagicSquaresInside(int[][] grid) {
        int n = grid.length, m = grid[0].length, ans = 0;
        for (int i = 0; i <= n - 3; i++) {  // Cleaner way to write boundary
            for (int j = 0; j <= m - 3; j++) {
                ans += check(grid, i, j);
            }
        }
        return ans;
    }

    private int check(int[][] grid, int x, int y) {
        int[] ct = new int[10];
        int rowSum = 0, colSum = 0, diag = 0, oppositeDiag = 0;

        // Check rows
        for (int i = x; i < x + 3; i++) {
            int currSum = 0;
            for (int j = y; j < y + 3; j++) {
                int curr = grid[i][j];
                if (curr < 1 || curr > 9) return 0;  // Fixed
                if (ct[curr] == 1) return 0;  // Use ct[curr] for 1-9

                currSum += curr;
                ct[curr] = 1;  // Mark as seen
            }
            if (rowSum == 0) rowSum = currSum;
            else if (rowSum != currSum) return 0;
        }

        // Check all numbers 1-9 are present
        for (int i = 1; i <= 9; i++) {
            if (ct[i] == 0) return 0;
        }

        // Check columns
        for (int j = y; j < y + 3; j++) {
            int currSum = 0;
            for (int i = x; i < x + 3; i++) {
                currSum += grid[i][j];
            }
            if (colSum == 0) colSum = currSum;
            else if (colSum != currSum) return 0;
        }

        // Check main diagonal
        for (int i = 0; i < 3; i++) {
            diag += grid[x + i][y + i];
        }

        // Check anti-diagonal
        for (int i = 0; i < 3; i++) {
            oppositeDiag += grid[x + i][y + 2 - i];
        }

        // Ensure all sums are equal
        if (diag != oppositeDiag) return 0;
        if (rowSum != colSum || rowSum != diag) return 0;  // Critical check!

        return 1;
    }
}