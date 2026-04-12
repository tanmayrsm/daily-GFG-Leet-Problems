class Solution {
    public boolean isToeplitz(int[][] mat) {
        // code here
        boolean ans = true;
        int n = mat.length, m = mat[0].length;
        for(int j = 0; j < m; j++) {
            ans = ans & check(1, j + 1, n, m, mat[0][j], mat);
            // System.out.println(j + "::" + mat[0][j] + "::" + ans);
            if(!ans)    return false;
        }
        for(int i = 1; i < n; i++) {
            ans = ans & check(i + 1, 1, n, m, mat[i][0], mat);
            if(!ans)    return false;
        }
        return ans;
    }
    private boolean check(int x, int y, int n, int m, int elem, int[][] mat) {
        while(x < n && y < m) {
            if(mat[x][y] != elem) {
                // System.out.println(x + "::" + y + "::" + elem);
                return false;
            }
            x++;
            y++;
        }
        return true;
    }
}