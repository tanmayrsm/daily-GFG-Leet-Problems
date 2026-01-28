
class Solution{
    private static int[][] d = new int[][] {{1,0}, {-1, 0}, {0, -1}, {0, 1}};
    static char[][] fill(int n, int m, char a[][])
    {
        // code here
        
        // replace boundary O with -
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if((i == 0 || i == n - 1 || j == 0 || j == m - 1) && a[i][j] == 'O')
                    dfs(a, i, j, 'O', '-');
            }
        }
        // replace inside O with S
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(a[i][j] == 'O')
                    dfs(a, i, j, 'O', 'X');
            }
        }
        // replace previously converted - with O
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(a[i][j] == '-')
                    a[i][j] = 'O';
            }
        }
        
        return a;
    }
    private static void dfs(char[][] a, int x, int y, char lookFor, char replacer) {
        a[x][y] = replacer;
        for(int i = 0; i < d.length; i++) {
            int xx = x + d[i][0];
            int yy = y + d[i][1];
            if(isValid(xx, yy, a.length, a[0].length) && a[xx][yy] == lookFor) {
                dfs(a, xx, yy, lookFor, replacer);
            }
        }
    }
    private static boolean isValid(int x, int y, int m, int n) {
        if(x < 0 || y < 0 || x >= m || y >= n)
            return false;
        return true;
    }
}