class Solution {
    public int[][] generateMatrix(int n) {
        int[][] ans = new int[n][n];
        int k = 0, l = 0, i, m = n;

        /*  k - starting row index
        m - ending row index
        l - starting column index
        n - ending column index
        i - iterator
        */
        
        int no = 1;

        while(l < n && k < m) {
            for(i = l; i < n; i++) {        // first row
                ans[k][i] = no++;
            }
            k++;

            for(i = k; i < m; i++) {        // last col
                ans[i][n - 1] = no++;
            }
            n--;

            if(k < m) {
                for(i = n - 1; i >= l; i--) {   // last row
                    ans[m - 1][i] = no++;
                }
                m--;
            }
            if(l < n) {
                for(i = m - 1; i >= k; i--){    // last col
                    ans[i][l] = no++;
                }
                l++;
            }
        }
        return ans;
    }
}