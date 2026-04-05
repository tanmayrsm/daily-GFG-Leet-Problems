class Solution {
    static ArrayList<Integer> diagView(int mat[][]) {
        // code here
        int n = mat.length, m = mat[0].length;
        ArrayList<Integer> ans = new ArrayList<>();
        for(int i = 0; i < m; i++) {
            slog(ans, 0, i, mat);
        }
        for(int i = 1; i < n; i++) {
            slog(ans, i, m - 1, mat);
        }
        return ans;
    }
    private static void slog(ArrayList<Integer> ans, int x, int y, int[][] mat) {
        while(x < mat.length && y >= 0) {
            ans.add(mat[x][y]);
            x++;
            y--;
        }
    }
}
