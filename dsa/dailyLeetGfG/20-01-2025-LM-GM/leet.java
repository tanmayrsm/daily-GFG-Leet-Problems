class Solution {
    public int firstCompleteIndex(int[] arr, int[][] mat) {
        int m = mat.length, n = mat[0].length;
        
        // Step 1: Map each number in mat to its (row, col) position
        Map<Integer, int[]> pos = new HashMap<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                pos.put(mat[i][j], new int[]{i, j});
            }
        }
        
        // Step 2: Initialize row and column paint counts
        int[] rowPaintCount = new int[m];
        int[] colPaintCount = new int[n];
        
        // Step 3: Process each number in arr
        for (int i = 0; i < arr.length; i++) {
            int num = arr[i];
            int[] position = pos.get(num);
            int row = position[0], col = position[1];
            
            // Paint the cell (increment row and column counts)
            rowPaintCount[row]++;
            colPaintCount[col]++;
            
            // Check if the row or column is completely painted
            if (rowPaintCount[row] == n || colPaintCount[col] == m) {
                return i;
            }
        }
        
        return -1; // This should not happen
    }
}