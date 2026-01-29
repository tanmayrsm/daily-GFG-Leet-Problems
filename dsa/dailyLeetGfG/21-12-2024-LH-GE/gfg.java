class Solution {
    // Function to rotate matrix anticlockwise by 90 degrees.
    static void rotateby90(int mat[][]) {
        // code here
        // reverse rows
        for(int i=0;i<mat.length;i++)
        {
            int start=0, end=mat.length-1;
            while(start<end)
            {
                int temp=mat[i][start];
                mat[i][start]=mat[i][end];
                mat[i][end]=temp;
                start++;
                end--;
            }
        }
        // transpose of matrix
        for(int i=0;i<mat.length-1;i++)
        {
            for(int j=i+1;j<mat.length;j++)
            {
                int temp=mat[i][j];
                mat[i][j]=mat[j][i];
                mat[j][i]=temp;
            }
        }
    }
}