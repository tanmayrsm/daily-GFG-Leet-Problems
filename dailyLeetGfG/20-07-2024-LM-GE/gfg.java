
/*

class Node{
    int data;
    Node left,right;
    Node(int d){
        data=d;
        left=right=null;
    }
}

*/

class Solution {
    // Return the root of the modified tree after removing all the half nodes.

    public Node RemoveHalfNodes(Node root) {
        // Code Here
        return removeHalf(root);
    }
    private static Node removeHalf(Node root) {
        if (root == null)   return root;
        root.left = removeHalf(root.left);
        root.right = removeHalf(root.right);
        if (root.left != null && root.right == null)
            return root.left;
        if (root.left == null && root.right != null)
            return root.right;
        return root;
    }
}