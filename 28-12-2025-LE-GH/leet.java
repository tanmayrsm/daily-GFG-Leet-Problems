class Solution {
    public int countNegatives(int[][] grid) {
        int n = grid.length, m = grid[0].length, count = 0, J = m - 1;
        for (int i = 0; i < n; ) {
            if (J >= 0 && grid[i][J] < 0) {
                while (J >= 0 && grid[i][J] < 0) {
                    count += n - i;
                    J--;
                }
            }
            i++;
        }
        return count;
    }
}
//  4  3  2 -1
//  3  2  1 -1
//  1  1 -1 -2
// -1 -1 -2 -3

