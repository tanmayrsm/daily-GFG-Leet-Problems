class Solution {
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

    public boolean isValidSudoku(char[][] board) {
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(!checkBox(i, j, board)) {
                    return false;
                }
            }
        }
        return true;
    }
}