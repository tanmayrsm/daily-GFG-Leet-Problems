class Solution {
    public void setZeroes(int[][] matrix) {
        boolean firstRowZero = false;
        boolean firstColZero = false;

        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[0].length; j++) {
                if(matrix[i][j] == 0){
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                    if(i == 0) firstRowZero = true;
                    if(j == 0) firstColZero = true;
                }
            }
        }


        for(int i = 1; i < matrix.length; i++) {
            if(matrix[i][0] == 0){
                setRow(matrix, i, 0);
            }
        }

        for(int i = 1; i < matrix[0].length; i++) {
            if(matrix[0][i] == 0){
                setCol(matrix, i, 0);
            }
        }
        // need to handle first row, col situation separately
        // as if first row has 0 somewhere, u will nullify that row, and next time column zero check will take whole row as 0 ...and set all cols as 0

        if(firstRowZero) {
            setRow(matrix,0 ,0);
        }
        if(firstColZero) {
            setCol(matrix, 0, 0);
        }

    }

    public void setRow(int[][] matrix, int rowNo, int no) {
        for(int i = 0; i < matrix[0].length; i++) {
            matrix[rowNo][i] = 0;
        }
    }

    public void setCol(int[][] matrix, int colNo, int no) {
        for(int i = 0; i < matrix.length; i++) {
            matrix[i][colNo] = 0;
        }
    }
}