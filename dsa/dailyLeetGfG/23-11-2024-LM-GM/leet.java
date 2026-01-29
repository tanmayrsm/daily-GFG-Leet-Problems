class Solution {
    public char[][] rotateTheBox(char[][] box) {
        int m = box.length;
        int n = box[0].length;
        
        // Step 1: Simulate the falling stones
        for (int i = 0; i < m; i++) {
            int empty = n - 1; // The position where the next stone should fall
            for (int j = n - 1; j >= 0; j--) {
                if (box[i][j] == '*') {
                    // Reset empty to just before the obstacle
                    empty = j - 1;
                } else if (box[i][j] == '#') {
                    // Move the stone to the empty position
                    box[i][j] = '.';
                    box[i][empty] = '#';
                    empty--;
                }
            }
        }
        
        // Step 2: Rotate the box 90 degrees clockwise
        char[][] rotated = new char[n][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                rotated[j][m - 1 - i] = box[i][j];
            }
        }
        
        return rotated;
    }
}