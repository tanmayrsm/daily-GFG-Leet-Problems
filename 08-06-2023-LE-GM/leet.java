class Solution {
    public int countNegatives(int[][] grid) {
        int ans = 0;
        int n = grid.length;
        int m = grid[0].length;
        for(int i = n-1; i >= 0; i--) { // trace from bottom right, cancel loop in case of any positive/zero element
            if(grid[i][m-1] > 0)
                break;
            for(int j = m - 1; j >= 0; j--) {
                if(grid[i][j] < 0)
                    ans++;
                else    break;
            }
        }
        return ans;
    }
}