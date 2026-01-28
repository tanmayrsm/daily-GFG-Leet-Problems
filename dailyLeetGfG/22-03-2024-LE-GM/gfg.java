
/*Complete the function below
Node is as follows:
class Node{
    int data;
    Node left,right;
    Node(int d){
        data=d;
        left=right=null;
    }
}
*/
class Tree {
    public static ArrayList <Integer> diagonalSum(Node root) 
    {
        // code here.x
        // I drew a BT of level 4, and saw the difference
        // u need to use BFS
        // add node in queue
            //  for each node in queue of size 'n', add all nodes, and their all right childs (dfs) as 
            //  sum for current diagonal level
            //  if u find a left child, add in queue for next iteration
        Queue<Node> q = new LinkedList<>();
        ArrayList<Integer> output = new ArrayList<>();
        q.add(root);
        
        while(!q.isEmpty()) {
            int n = q.size();
            int ans = 0;
            while(n-- > 0) {
                Node x = q.poll();
                int curr = 0;
                
                while(x != null) {
                    curr += x.data;
                    if(x.left != null)
                        q.offer(x.left);
                    x = x.right;
                }
                ans += curr;
            }
            output.add(ans);
        }
        
        return output;
    }
    private static int rightSubTree(Node x) {
        int curr = 0;
        while(x != null) {
            curr += x.data;
            x = x.right;
        }
        return curr;
    }
}