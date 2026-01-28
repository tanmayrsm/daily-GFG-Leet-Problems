
class Solution
{
    public boolean isWordExist(char[][] board, String word)
    {
        // Code here
        char[] ch = word.toCharArray();
        int n = board.length;
        int m = board[0].length;
        boolean[][] vis = new boolean[n][m];
        for(int i = 0; i < n; i++)
            for(int j = 0; j < m; j++) {
                if(board[i][j] == ch[0]) {
                    boolean doesWordExists = dfs(board, n, m, i, j, ch, 0, vis);
                    if(doesWordExists)
                        return true;
                }
            }
        return false;
    }
    
    private static boolean dfs(char[][] board, int n, int m, int x, int y, char[] ch, int currIndex, boolean[][] vis) {
        if(currIndex == ch.length)
            return true;
        if(x < 0 || y < 0 || x >= n || y >= m || vis[x][y] || ch[currIndex] != board[x][y])
            return false;
        // System.out.println("index :: " + currIndex);
        vis[x][y] = true;
        boolean left = dfs(board, n, m, x, y - 1, ch, currIndex + 1, vis);
        boolean right = dfs(board, n, m, x, y + 1, ch, currIndex + 1, vis);
        boolean top = dfs(board, n, m, x - 1, y, ch, currIndex + 1, vis);
        boolean bottom = dfs(board, n, m, x + 1, y, ch, currIndex + 1, vis);
        vis[x][y] = false;
        return left || right || top || bottom;
    }
}