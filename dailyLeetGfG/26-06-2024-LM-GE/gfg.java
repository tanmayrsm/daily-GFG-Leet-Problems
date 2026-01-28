
// User function Template for Java

class Solution {
    public int findCoverage(int[][] matrix) {
        // code here
        int ans = 0;
        for(int i = 0; i < matrix.length; i++) 
            for(int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    if (i - 1 >= 0 && matrix[i - 1][j] == 1)    ans++;
                    if (j - 1 >= 0 && matrix[i][j - 1] == 1)    ans++;
                    if (i + 1 < matrix.length && matrix[i + 1][j] == 1)    ans++;
                    if (j + 1 < matrix[0].length && matrix[i][j + 1] == 1)    ans++;
                }
            }
        return ans;
    }
}