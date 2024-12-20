class Solution {
    // Function to return a list of integers denoting spiral traversal of matrix.
    public ArrayList<Integer> spirallyTraverse(int matrix[][]) {
        // code here
        // code here
        ArrayList<Integer> res=new ArrayList<>();
        int n=matrix.length,m=matrix[0].length;
        int d=0,l=0,r=m-1,u=n-1,c=0;
        while(c!=n*m) {
            for(int i=l;i<=r;i++) {
                res.add(matrix[d][i]);
                c+=1;
            }
            d+=1;
            for(int i=d;i<=u;i++) {
                res.add(matrix[i][r]);
                c+=1;
            }
            if(c==n*m) break;
            r-=1;
            for(int i=r;i>=l;i--) {
                res.add(matrix[u][i]);
                c+=1;
            }
            u-=1;
            for(int i=u;i>=d;i--) {
                res.add(matrix[i][l]);
                c+=1;
            }
            l+=1;
        }
        return res;
    }
}