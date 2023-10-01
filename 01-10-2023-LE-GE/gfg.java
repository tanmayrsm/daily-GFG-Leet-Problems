
class Solution
{
    //Function to return list of integers that form the boundary 
    //traversal of the matrix in a clockwise manner.
    static ArrayList<Integer> boundaryTraversal(int matrix[][], int n, int m)
    {
        // code here 
        ArrayList<Integer> ans = new ArrayList<>();
        // edge case
        if(n == 1 || m == 1) { // O(n) || O(m) complexity
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < m; j++)
                    ans.add(matrix[i][j]);
            }
            return ans;
        }
        
        String dir = "RIGHT";
        int l = 0, r = 0;
        do {
            switch(dir) {
                case "RIGHT" :{
                    // only col changes from 0 to m
                    while(r < m - 1) {
                        ans.add(matrix[l][r]);
                        r++;
                    }
                    dir = "BOTTOM";
                    break;
                }
                case "BOTTOM" : {
                    // only row changes from 0 to n
                    while(l < n - 1) {
                        ans.add(matrix[l][r]);
                        l++;
                    }
                    dir = "LEFT";
                    break;
                }
                case "LEFT" : {
                    // only col changes from m to 0
                    while(r > 0) {
                        ans.add(matrix[l][r]);
                        r--;
                    }
                    dir = "TOP";
                    break;
                }
                case "TOP" : {
                    // only row changes from n to 0
                    while(l > 0) {
                        ans.add(matrix[l][r]);
                        l--;
                    }
                    dir = "RIGHT";
                    break;
                }
            }
        } while(l != 0 || r != 0);
        
        return ans;
    }
}
