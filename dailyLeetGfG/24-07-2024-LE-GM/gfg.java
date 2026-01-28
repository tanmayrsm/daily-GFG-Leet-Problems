
class Solution {
    private static int prev;
    // Function to check whether a Binary Tree is BST or not.
    boolean isBST(Node root) {
        prev = -1;
    
    }
    boolean solve(Node root, int prev) {
        // code here.
        if (root == null)   return true;
        boolean l = solve(root.left);
        if (prev > root.data)
            return false;
        boolean r = solve(root.right);
        
        return l && r;
    }
    
}