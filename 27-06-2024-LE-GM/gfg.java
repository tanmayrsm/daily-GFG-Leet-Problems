
class Solution {
    /*You are required to complete this method*/
    boolean isToepliz(int mat[][]) {
        // Your code here
        for(int i=1;i<mat.length;i++){
            for(int j=1;j<mat[i].length;j++){
                if(mat[i][j]!=mat[i-1][j-1]) return false;
            }
        }
        return true;

    }
}