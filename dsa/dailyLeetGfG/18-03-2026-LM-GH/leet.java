class Solution {
    public int countSubmatrices(int[][] grid, int k) {
        int n = grid.length, m = grid[0].length, ans = 0;
        for(int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int top = (i - 1) >= 0 ? grid[i - 1][j] : 0;
                int left = (j - 1) >= 0 ? grid[i][j - 1] : 0;
                int tL = (i >= 1 && j >= 1) ? grid[i - 1][j - 1] : 0;
                grid[i][j] += top + left - tL;
                if(grid[i][j] <= k) ans++;
            }
        }
        return ans;
    }
}