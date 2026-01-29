class Solution {
    public ArrayList<Integer> findPeakGrid(int[][] mat) {
        // code here
        int n = mat.length, m = mat[0].length;
        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int left = getPos(i, j - 1, mat);
                int right = getPos(i, j + 1, mat);
                int top = getPos(i + 1, j, mat);
                int bottom = getPos(i - 1, j, mat);
                int curr = mat[i][j];
                if (curr >= left && curr >= right && curr >= top && curr >= bottom){ ans.add(i); ans.add(j); return ans;}
            }
        }
        // System.out.println(ans);
        return ans;
    }
    private int getPos(int x, int y, int[][] mat) {
        if(x < 0 || y < 0 || x >= mat.length || y >= mat[0].length) return Integer.MIN_VALUE;
        return mat[x][y];
    }
}