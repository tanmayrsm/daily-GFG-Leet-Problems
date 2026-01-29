class Solution {
    public static int balanceSums(int[][] mat) {
        // code here
        int n = mat.length, m = mat[0].length, maxo = 0, ans = 0;
        int[] row = new int[n];
        int[] col = new int[m];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++) {
                row[i] += mat[i][j];
                col[j] += mat[i][j];
                maxo = Math.max(maxo, Math.max(row[i], col[j]));
            }
        // try to make all sum equal to maxo
        for (int i = 0; i < n; i++) {
            int res = maxo - row[i];
            ans += res;
        }

        return ans;
    }
}

//[1, 2, 3] 6
//[4, 2, 3] 9
//[3, 2, 1] 6
// 8  6  7
//max no is 9, so every col, row must be 9
//
//6 -> 8 6 7 -> 9 8 7 (3)
//9 -> null
//6 -> 9 9 9 (3)