class Solution {
    private static int[][] dir = new int[][] {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};
    public boolean exist(char[][] board, String word) {
        char[] w = word.toCharArray();
        int n = board.length, m = board[0].length;
        boolean[][] vis = new boolean[n][m];
        
        for(int i = 0; i < n; i++)
            for(int j = 0; j < m; j++) {
                vis[i][j] = true;
                if(board[i][j] == w[0] && check(board, i, j, vis, w, 1))
                    return true;
                vis[i][j] = false;
            }

        return false;
    }
    private static boolean check(char[][] board, int x, int y, boolean[][] vis, char[] word, int curr) {
        if(curr == word.length) return true;
        boolean ans = false;
        for(int[] d : dir) {
            int xx = x + d[0], yy = y + d[1];
            if(xx >= 0 && yy >= 0 && xx < board.length && yy < board[0].length && !vis[xx][yy] && board[xx][yy] == word[curr]) {
                vis[xx][yy] = true;
                ans = ans || check(board, xx, yy, vis, word, curr + 1);
                vis[xx][yy] = false;
            } 
        }
        return ans;

    }
}