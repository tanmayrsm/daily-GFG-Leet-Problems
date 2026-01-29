class Solution {
    public int maxGold(int[][] mat) {
        // code here
        int ans = 0;
        for (int j = 1; j < mat[0].length; j++) {
            for (int i = 0; i < mat.length; i++) {
                int leftTop = (i - 1 >= 0) ? mat[i - 1][j - 1] : 0;
                int leftBottom = (i + 1 < mat.length) ? mat[i + 1][j - 1] : 0;
                mat[i][j] = mat[i][j] + Math.max(leftTop, Math.max(mat[i][j - 1], leftBottom));
                if (j == mat[0].length - 1)
                    ans = Math.max(ans, mat[i][j]);
            }
        }
        return ans;
    }
}
/* 
[[1, 3, 3], [2, 1, 4], [0, 6, 4]]

1 3 3
2 1 4
0 6 4

*/



