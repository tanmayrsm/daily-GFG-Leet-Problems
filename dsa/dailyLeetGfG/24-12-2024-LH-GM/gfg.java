class Solution {
    // Function to search a given number in row-column sorted matrix.
    public boolean searchMatrix(int[][] mat, int x) {
        // code here
        int n = mat.length, m = mat[0].length, probRow = -1, l = 0, r = mat.length - 1;
        // find perfect row using BS
        while (l <= r) {
            int mid = (l + r) / 2;
            if (mat[mid][m - 1] == x)   return true;
            if (mat[mid][m - 1] > x) {
                probRow = mid;
                r = mid - 1;
            } else l = mid + 1;
        }
        // System.out.println("probrow ::" + probRow);
        if (probRow == -1)  return false;
        l = 0; r = mat[0].length - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (mat[probRow][mid] == x) return true;
            if (mat[probRow][mid] > x) {
                r = mid - 1;
            } else l = mid + 1;
        }
        
        return false;
        
    }
}