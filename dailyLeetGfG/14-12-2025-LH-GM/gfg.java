class Solution {
    public ArrayList<Integer> prefixSum2D(int[][] mat, int[][] queries) {
        // code here
        int n = mat.length, m = mat[0].length;
        ArrayList<Integer> ans = new ArrayList<>();
        // calculate prefix sum from (0,0) to (i, j)
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                mat[i][j] += ((i >= 1) ? mat[i - 1][j] : 0) + ((j >= 1) ? mat[i][j - 1] : 0) - ((i >= 1 && j >= 1) ?
                        mat[i - 1][j - 1] : 0);
            }
        }
        for (int[] q :  queries) {
            int x = q[0], y = q[1], xdash = q[2], ydash = q[3];
            int res = getNo(mat, xdash, ydash) - getNo(mat, x - 1, y - 1) -
                    (getNo(mat, x - 1, ydash) - getNo(mat, x - 1, y - 1)) - (getNo(mat, xdash, y - 1) - getNo(mat,
                    x - 1,
                    y - 1));
            ans.add(res);
        }
        return ans;
    }
    private int getNo(int[][] mat, int x, int y) {
        if (x >= 0 && y >= 0 && x < mat.length && y < mat[0].length)    return mat[x][y];
        return 0;
    }
}
