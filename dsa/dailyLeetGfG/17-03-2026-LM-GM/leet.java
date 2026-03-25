class Solution {
    public int largestSubmatrix(int[][] matrix) {
        int n = matrix.length, m = matrix[0].length, ans = 0;
        for(int i = 1; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(matrix[i][j] > 0)
                    matrix[i][j] += matrix[i - 1][j];
            }
        }
        for(int i = 0; i < n; i++) {
            Arrays.sort(matrix[i]);
            int width = 1;
            for (int j = m - 1; j >= 0 && matrix[i][j] > 0; j--) {
                ans = Math.max(ans, matrix[i][j] * width);
                width++;
            }
        }
        return ans;
    }
}

