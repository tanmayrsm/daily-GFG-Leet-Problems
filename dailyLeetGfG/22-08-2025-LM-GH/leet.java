class Solution {
    private int MAX = Integer.MAX_VALUE, MIN = Integer.MIN_VALUE;
    public int minimumArea(int[][] grid) {
        int n = grid.length, m = grid[0].length, left = MAX, right = MIN, top = MAX, bottom = MIN;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    left = Math.min(left, j);
                    right = Math.max(right, j);
                    top  = Math.min(top, i);
                    bottom = Math.max(bottom, i);
                }
            }
        }
        if (left != MAX) {
            return (bottom - top + 1) * (right - left + 1);
        }
        return 0;
    }

}