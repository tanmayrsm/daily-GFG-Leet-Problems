class Solution {
    public boolean ValidCorner(int mat[][]) {
        // Code here
        int n=mat.length;
        int m= mat[0].length;
        boolean matched=false;
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                matched=false;
                for(int k=0;k<m;k++){
                    if(mat[i][k]==1&&mat[i][k]==mat[j][k]){

                        if(matched)
                            return true;
                        else
                            matched=true;
                    }
                }
            }
        }
        return false;
    }
}