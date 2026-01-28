
//User function Template for Java

class Solution {
    int countPairs(int mat1[][], int mat2[][], int n, int x) {
        // code here
        int ans = 0;
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++) {
                int searchFor = x - mat1[i][j];
                if(searchFor > 0) {
                    int inRow = bsCol(mat2, searchFor, n);
                    if(inRow != -1) {
                        int row = bsRow(mat2, searchFor, n, inRow);
                        if(row != -1) {
                            // System.out.println(inRow + ":" + row);
                            ans++;
                        }
                    }
                    
                }
            }
        return ans;
    }
    private static int bsCol(int[][] mat2, int no, int n) {
        int l = 0, r = n - 1, ans = -1;
        while(l <= r) {
            int mid = (l + r) / 2;
            if(mat2[mid][n - 1] == no)
                return mid;
            else if (mat2[mid][n - 1] > no) {
                ans = mid;
                r = mid - 1;
            } else l = mid + 1;
        }
        return ans;
    }
    
    private static int bsRow(int[][] mat2, int no, int n, int rowNo) {
        int l = 0, r = n - 1;
        while(l <= r) {
            int mid = (l + r) / 2;
            if(mat2[rowNo][mid] == no)
                return mid;
            else if (mat2[rowNo][mid] > no) {
                r = mid - 1;
            } else l = mid + 1;
        }
        return -1;
    }
}