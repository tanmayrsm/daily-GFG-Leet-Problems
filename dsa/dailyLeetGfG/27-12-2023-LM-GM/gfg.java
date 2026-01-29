
class Solution
{
    public int[] antiDiagonalPattern(int[][] matrix)
    {
        // Code here
        int n = matrix.length, ct = 0;
        int[] ans = new int[n * n];
        
        for(int j = 0; j < n; j++) {
            // ans[ct++] = matrix[0][j];
            for(int i = 0; i <= j; i++) {
                ans[ct++] = matrix[i][j - i];
            }
        }
            
        
        for(int i = 1; i < n; i++) {
            int ctn = 0;
            for(int j = n - 1; j >= i; j--) {
                ans[ct++] = matrix[i + ctn][j];
                ctn++;
            }
        }
        
        return ans;

    }
}