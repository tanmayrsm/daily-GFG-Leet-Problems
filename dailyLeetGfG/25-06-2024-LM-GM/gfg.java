

// User function template for java

class Solution {
    int[][] rotateMatrix(int k, int mat[][]) {
        // code here
        int n = mat.length, m = mat[0].length;
        int[][] ans = new int[n][m];
        for(int j = 0; j < m; j++) {
            int newCol = j - k % m;
            if (newCol < 0)
                newCol += m;
            for(int i = 0; i < n; i++) {
                ans[i][newCol] = mat[i][j];
            }
        }
        return ans;
    }
}

// Input: k=1, mat=[[1,2,3],[4,5,6],[7,8,9]]
// Output:
// 2 3 1
// 5 6 4
// 8 9 7
// Explanation: Rotate the matrix by one
// 1 2 3       2 3 1
// 4 5 6  =>  5 6 4
// 7 8 9       8 9 7

// Input: k=2, mat=[[1,2,3],[4,5,6],[7,8,9]]
// Output:
// 3 1 2
// 6 4 5
// 9 7 8
// Explanation: After rotating the matrix looks like
// 1 2 3       2 3 1       3 1 2
// 4 5 6  =>  5 6 4  =>   6 4 5
// 7 8 9       8 9 7       9 7 8

// k = 100, n = 3
// new col => curr - k%n
// 0 -> -1, 1 -> 0, 2 -> 1

// k = 100, n = 6
// col => curr - k%n => curr - 2
// 0 -> -2, 1 -> -1, 2 -> 0, 3 -> 1, 4 -> 2, 5 -> 3
// if negative => n - abs(negative no)
// 0 -> 6 - 2 -> 4
// 1 -> 6 - 1 -> 5