07-05-2023-LH-GEaclass Solution {
    public int diagonalSum(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;
        int ans = 0;
        for(int i = 0; i < n; i++)
            for(int j = 0; j < m; j++)
                if(i == j || i + j == n - 1)    // i == j => condition for '\' left diagonal elements
                    ans += mat[i][j];           // i + j == n - 1 => condition for  '/' right diagonal elements
        return ans;
    }
}