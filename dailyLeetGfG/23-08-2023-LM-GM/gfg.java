
class Solution
{
    private static int n, m;
    private static int[][] d = new int[][] {{0, -1}, {0, 1}, {1, 0}, {-1, 0}, {-1, -1}, {1, -1}, {-1, 1}, {1, 1}};
    public int[][] searchWord(char[][] grid, String word)
    {
        // Code here
        List<int[]> ans = new ArrayList<>();
        n = grid.length; m = grid[0].length;
        
        for(int i = 0; i < n; i++)
            for(int j = 0; j < m; j++) {
                if(word.charAt(0) == grid[i][j] && check(i, j, grid, word.substring(1))) {
                    ans.add(new int[] {i, j});
                }
            }
        
        int[][] j = new int[ans.size()][2];
        for(int i = 0; i < ans.size(); i++) {
            j[i][0] = ans.get(i)[0];
            j[i][1] = ans.get(i)[1];
        }
        return j;
    }
    
    private static boolean check(int x, int y, char[][] grid, String word) {
        for(int i = 0; i < d.length; i++) {
            
            int xx = x + d[i][0], yy = y + d[i][1];
            int ct = 0;
            while(isValid(xx, yy) && ct < word.length() && grid[xx][yy] == word.charAt(ct)) {
                ct++;
                xx += d[i][0] ;
                yy += d[i][1];
            }        
            if(ct == word.length())
                return true;
        }
        return false;
    }
    
    private static boolean isValid(int x, int y) {
        if(x < 0 || y < 0 || x >= n || y >= m)  return false;
        return true;
    }
}