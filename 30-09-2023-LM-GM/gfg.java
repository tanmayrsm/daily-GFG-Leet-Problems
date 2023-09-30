
class Solution
{
    //Function to modify the matrix such that if a matrix cell matrix[i][j]
    //is 1 then all the cells in its ith row and jth column will become 1.
    void booleanMatrix(int matrix[][])
    {
        // code here 
        int n = matrix.length, m = matrix[0].length;
        Set<Integer> doOneRow = new HashSet<>();
        Set<Integer> doOneCol = new HashSet<>();
        
        for(int i = 0; i < n; i++)
            for(int j = 0; j < m; j++) {
                if(matrix[i][j] == 1) {
                    doOneRow.add(i);
                    doOneCol.add(j);
                }
            }
        
        for(int i = 0; i < n; i++)
            for(int j = 0; j < m; j++)
                if(matrix[i][j] == 0 && doOneRow.contains(i) || doOneCol.contains(j))
                    matrix[i][j] = 1;
                    
        return;
            
        
    }
}