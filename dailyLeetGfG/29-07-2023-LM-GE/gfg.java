
class Tree
{
    private static int totalNodes;
    private static int L;
    private static int R;
    private static Integer Lval;
    private static Integer Rval;
    
    
    public static float findMedian(Node root)
    {
        // code here.
        L = -2;
        R = -2;
        Lval = -1;
        Rval = -1;
        totalNodes = noOfNodes(root);
        if(totalNodes == 1) return root.data;
        
        if(totalNodes % 2 == 0) {
            L = totalNodes / 2;
            R = totalNodes / 2 - 1;
        } else  L = totalNodes / 2;
        
        solve(root, 0);
        
        if(Rval == -1)  return Lval;
        return ((float)Rval + (float)Lval) / 2;
    }
    
    private static int solve(Node root, int index) {
        if(root == null) {
            return -1;
        }
        int l = solve(root.left, index);
        int currIndex = l == -1 ? index : l + 1;
        int r = solve(root.right, currIndex + 1);
        
        
        if(currIndex == L)
            Lval = root.data;
        if(currIndex == R)
            Rval = root.data;
        
        return r == -1 ? currIndex : r ;
    }
    
    private static int noOfNodes(Node n) {
        if(n == null) return 0;
        int ct = noOfNodes(n.left) + noOfNodes(n.right);
        return 1 + ct;
    }
}