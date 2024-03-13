

class Solution {
    public int[] matrixDiagonally(int[][] mat) {
        int n = mat.length, ct = 0;
        int[] ans = new int[n * n];
        boolean upToDown = false;
        
        // track upper triangle
        for(int j = 0; j < n; j++) {
            for(int i = 0; i <= j; i++) {
                if(upToDown)
                    ans[ct++] = mat[i][j - i];
                else ans[ct++] = mat[j - i][i];
            }
            upToDown = !upToDown;
        }
        
        // track lower triangle
        for(int i = 1; i < n; i++) {
            for(int j = n - 1; j >= i; j--) {
                if(upToDown)    
                    ans[ct++] = mat[i + (n - 1 - j)][j];
                else ans[ct++] = mat[j][i + (n - 1 - j)];
            }
            upToDown = !upToDown;
        }
        
        return ans;
    }
}