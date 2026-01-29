// function Template for Java

// FUNCTION CODE
/* A Binary Tree node
class Node
{
    int data;
    Node left, right;
   Node(int item)
   {
        data = item;
        left = right = null;
    }
} */

class Solution {
    // Function to convert a binary tree into its mirror tree.
    void mirror(Node node) {
        // Your code here
        dfs(node);
    }
    private static Node dfs(Node node) {
        if (node == null) 
            return null;
        dfs(node.left);
        dfs(node.right);
        node.left = node.right;
        node.right = node.left;
    }
}