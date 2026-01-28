class Solution {
    public long maxMatrixSum(int[][] matrix) {
        int mino = Integer.MAX_VALUE;
        int n = matrix.length;
        long ans = 0;
        int isNega = 0;
        for (int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                mino = Math.min(mino, Math.abs(matrix[i][j]));
                if(matrix[i][j] < 0)
                    isNega++;
                ans += Math.abs(matrix[i][j]);
            }
        }
        return (isNega % 2 == 1) ? ans - mino*2 : ans;
    }
}