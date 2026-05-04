class Solution {
    public void rotate(int[][] matrix) {
        int n = matrix.length, len = n - 1, f = 0;
        for(int i = 0; i < n / 2; i++) {
            solve(matrix, i, i, len, f);
            len--;
            f++;
        }
    }
    private void solve(int[][] arr, int x, int y, int len, int f) {
        int yextreme = y + len - f, xtreme = x + len - f, X = x, Y = y;
        int yv = yextreme,xv = x, yv2 = yextreme, xv2 = xtreme, yv3 = y, xv3 = xtreme;
        for(int j = x; j < xtreme; j++) {
            int curr = arr[x][y];
            int next = arr[xv][yv];
            int next2 = arr[xv2][yv2];
            int next3 = arr[xv3][yv3];

            arr[xv++][yv] = curr;
            arr[xv2][yv2--] = next;
            arr[xv3--][yv3] = next2;
            arr[x][y++] = next3;
        }
    }
}