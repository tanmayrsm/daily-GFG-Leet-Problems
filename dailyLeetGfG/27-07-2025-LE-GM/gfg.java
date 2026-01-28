class Solution {
    public void setMatrixZeroes(int[][] mat) {
        // code here
        Set<Integer> rows = new HashSet<>();
        Set<Integer> cols = new HashSet<>();
        int n = mat.length, m = mat[0].length;
        for (int i = 0; i < n; i++) 
            for (int j = 0; j < m; j++) {
                if (mat[i][j] == 0) {
                    rows.add(i);
                    cols.add(j);
                }
            }
        for (int i = 0; i < n; i++) 
            for (int j = 0; j < m; j++) {
                if (mat[i][j] != 0 && (rows.contains(i) || cols.contains(j)))
                    mat[i][j] = 0;
            }
    }
}