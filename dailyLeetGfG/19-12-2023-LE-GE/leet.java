class Solution {
    public int[][] imageSmoother(int[][] img) {
        int n = img.length, m = img[0].length;
        int[][] ans = new int[n][m];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                ans[i][j] = getVal(i, j, img);
            }
        }
        return ans;
    }
    private static int getVal(int i, int j, int[][] img) {
        int val = 0, ct = 0;
        for(int l = i - 1; l <= i + 1; l++) {
            for(int r = j - 1; r <= j + 1; r++) {
                if(l >= 0 && r >= 0 && l < img.length && r < img[0].length) {
                    val += img[l][r];
                    ct++;
                }
            }
        }
        return (int)(val / ct);
    }
}