class Solution {
    // VALID SUDOKU code - START
    private boolean checkRow(int row, char no, char[][] board) {
        int ct = 0;
        for(int i = 0; i < 9; i++) {
            if(board[row][i] == no) {
                ct++;
                if(ct > 1) {
                    return false;
                }
            }
        } 
        return true;
    }
    
    private boolean checkCol(int col, char no, char[][] board) {
        int ct = 0;
        for(int i = 0; i < 9; i++) {
            if(board[i][col] == no) {
                ct++;
                if(ct > 1) {
                    return false;
                }
            }
        } 
        return true;
    }
    
    private boolean checkBox(int start, int end, char[][] board) {
        int[] visited = new int[9];
        for(int i = start*3; i < start*3 + 3; i++) {
            for(int j = end*3; j < end*3 + 3; j++) {
                if(board[i][j] != '.') {
                    int currNum = board[i][j] - '0' - 1;
                    if(visited[currNum] == 1) {
                        return false;
                    }
                    visited[currNum] = 1;
                    if(!checkRow(i, board[i][j], board) || !checkCol(j, board[i][j], board)) {
                        return false;
                    }
                }
            }
        }
        return true;
    } 
    
    public boolean isValid(char[][] board) {
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(!checkBox(i, j, board)) {
                    return false;
                }
            }
        }
        return true;
    }
    // END
    private static boolean isValidEfficient(int row, int col, char c, char[][] board) {
    // check if char c is in same row / col / box
    // System.out.println("try ::" + c);
            
    for(int i = 0; i < 9; i++) {
      if(board[row][i] == c)  return false;
      if(board[i][col] == c)  return false;
      if(board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] == c)  return false;
    }
    return true;
  }
    private boolean done(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == '.') { 
                    for (int k = 1; k <= 9; k++) {
                        char put = (char)('0' + k);
                        if (isValidEfficient(i, j, put, board)) {
                            board[i][j] = put;
                            if(done(board))  return true;
                            board[i][j] = '.';
                        } 
                    }
                    return false;
                }
            }
        }
        return true;
    }
    public void solveSudoku(char[][] board) {
        done(board);
    }
}