
class Solution
{
    int[][] d = new int[][] { {0, -1}, {0, 1}, {1, 0}, {-1, 0}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
    public int  Count(int[][] matrix)
    {
        // code here
        int n = matrix.length, m = matrix[0].length;
        int ans = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(matrix[i][j] == 1) {
                    int ct = 0;
                    for(int k = 0; k < d.length; k++) {
                        int xx = i + d[k][0];
                        int yy = j + d[k][1];
                        if(isValid(xx, yy, n, m) && matrix[xx][yy] == 0)
                            ct++;
                    }
                    if(ct % 2 == 0 && ct > 0)
                        ans++;
                }
            }
        }
        return ans;
    }
    
    private static boolean isValid(int x, int y, int n, int m) {
        if(x < 0 || y < 0 || x >= n || y >= m)
            return false;
        return true;
    }
}