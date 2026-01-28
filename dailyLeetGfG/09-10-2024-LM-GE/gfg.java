
/*

class Node
{
   int data;
   Node right,down;

   Node(int data){
       this.data = data;
       right = null;
       down = null;
   }
}
*/
/*Function should return the head of the 2D LL.*/

class Solution {
    static Node construct(int arr[][]) {
        // Add your code here
        int n = arr.length, m = arr[0].length;
        Node ans = null;
        Node startOfNextRow = null;
        for(int i = 0; i < n; i++) {

            Node lastInRow = null;  // horizontal previous node
            Node nextLastInRow = null;  // next rows horizontal previous node
            Node newer = null;
            for(int j = 0; j < m; j++) {
                if (i == 0) {   // first row
                    newer = new Node(arr[i][j]);
                } else {    // second row onwards
                    if (j == 0)
                        newer = startOfNextRow;
                    else newer = newer.right;
                }
                // one time
                if(ans == null)
                    ans = newer;
                    
                // previous pointer in row
                if (lastInRow != null) 
                    lastInRow.right = newer;
                lastInRow = newer;
                
                if (i + 1 < n) {
                    // downward node creation
                    Node down = new Node(arr[i + 1][j]);
                    if (j == 0)
                        startOfNextRow = down;  // mark start of next row
                    newer.down = down;
                    
                    // set previous pointer in row (of next row)
                    if (nextLastInRow == null) {
                        nextLastInRow = down;
                    } else {
                        nextLastInRow.right = down;
                        nextLastInRow = down;
                    }
                }   
            }
        }
        return ans;
    }
}