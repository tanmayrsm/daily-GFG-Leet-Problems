class Solution {
    public char[][] rotateTheBox(char[][] arr) {
        int n = arr.length, m = arr[0].length;
        char[][] res = new char[m][n];
        for(int i = n - 1; i >= 0; i--) {
            for(int j = 0; j < m; j++) {
                res[j][ n - 1 - i] = arr[i][j];
            }
        }

        for(int j = 0; j < n; j++) {
            push(res, j, m, n);
        }
        return res;
    }
    private void push(char[][] res, int col, int m, int n) {
        int ct = 0;
        for(int i = 0; i < m; i++) {
            if(res[i][col] == '#') {
                ct++;
                res[i][col] = '.';
            } else if (res[i][col] == '*') {
                int prev = i - 1;
                while(ct > 0 && prev >= 0) {
                    res[prev--][col] = '#';
                    ct--;
                }
            }
        }
        int prev = m - 1;
        while(ct > 0 && prev >= 0) {
            res[prev--][col] = '#';
            ct--;
        }
    }
}
