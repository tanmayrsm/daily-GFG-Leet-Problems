
// User function Template for Java

class Solution {
    int largestSubsquare(int n, char a[][]) {
        // code here
        int[][] row = new int[n][n];
        int[][] col = new int[n][n];
        int ans = 0;
        
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++) {
                if(a[i][j] == 'X') { 
                    if(j - 1 >= 0 && a[i][j - 1] == 'X')
                        row[i][j] = 1 + row[i][j - 1];
                    else row[i][j] = 1;
                    ans = 1;
                }
            }
            
        for(int j = 0; j < n; j++) {
            for(int i = 0; i < n; i++)
                if(a[i][j] == 'X') {
                    if(i - 1 >= 0 && a[i - 1][j] == 'X')
                        col[i][j] = 1 + col[i - 1][j];
                    else col[i][j] = 1;
                    if(col[i][j] > 1 && row[i][j] > 1) {
                        // reff logic -
                        int currentValue = Math.min(col[i][j], row[i][j]);
                        while(currentValue > 0) {
                            int top1 = i - currentValue + 1;
                            int left1 = j - currentValue + 1;
                            if ((row[top1][j] >= currentValue) && (col[i][left1] >= currentValue)) {
                                ans = Math.max(ans, currentValue);
                                break;
                            }
                            currentValue--;
                        }
                    }
                }
            }
        return ans;
    }
};