class Solution {
    public int countSquares(int[][] matrix) {
        int n = matrix.length, m = matrix[0].length, ans = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(matrix[i][j] == 1 && i - 1 >= 0 && j - 1 >= 0) 
                    matrix[i][j] = Math.min(matrix[i - 1][j], 
                                   Math.min(matrix[i - 1][j - 1], matrix[i][j - 1])) + 1;
                if (matrix[i][j] > 0)
                    ans += matrix[i][j];
            }
        }
        
        return ans;
    }
}