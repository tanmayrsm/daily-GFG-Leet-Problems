class Solution {
    private static int bsRow(int[][] matrix, int l, int r, int rowNo, int target) {  
        // bin search on particular row No
        while(l <= r) {
            int mid = (l + r) / 2;
            if(matrix[rowNo][mid] == target)
                return mid;
            else if (matrix[rowNo][mid] > target) {
                r = mid - 1;
            }
            else {
                l = mid + 1;
            }
        }
        return -1 ;
    }
    
    private static int bsCol(int[][] matrix, int l, int r, int colNo, int target) {  
        // bin search on particular col No
        int expectedRow = -1;
        while(l <= r) {
            int mid = (l + r) / 2;
            if(matrix[mid][colNo] == target)
                return mid;
            else if (matrix[mid][colNo] > target) {
                expectedRow = mid;
                r = mid - 1;
            }
            else {
                l = mid + 1;
            }
        }
        return expectedRow == -1 ? 0 : expectedRow;
    }
    public boolean searchMatrix(int[][] matrix, int target) {
        int n = matrix.length;
        int m = matrix[0].length;
        int rowNo = bsCol(matrix, 0, n - 1, m - 1, target);
        return bsRow(matrix, 0, m - 1, rowNo, target) != -1 ? true : false;
    }
}