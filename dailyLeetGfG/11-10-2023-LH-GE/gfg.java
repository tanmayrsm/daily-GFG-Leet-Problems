
class Tree
{
    
    //Function to check whether a binary tree is balanced or not.
    static boolean isBal;
    boolean isBalanced(Node root)
    {
	    // Your code here
	    isBal = true;
	    dfs(root);
	    return isBal;
    }
    private static int dfs(Node root) {
        int l = root.left == null ? 0 :  (dfs(root.left) + 1);
        int r = root.right == null ? 0 : (dfs(root.right) + 1);
        
        if(Math.abs(l - r) > 1)
            isBal = false;
        return Math.max(l, r);
    }
}

