class Solution {
    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;
        int minSum = Integer.MAX_VALUE;
        for(int i = 0; i < n; i++) {    
            minSum = Integer.MAX_VALUE;
            for(int j = 0; j < n; j++) {
                if(i > 0)
                matrix[i][j] = matrix[i][j] + 
        Math.min((j > 0 ? matrix[i - 1][j - 1] : Integer.MAX_VALUE) ,  
                    Math.min(matrix[i - 1][j], (j < n-1 ? matrix[i - 1][j + 1] : Integer.MAX_VALUE)));
                
                minSum = Math.min(minSum, matrix[i][j]);
            }
        }
        return minSum;
    }
}