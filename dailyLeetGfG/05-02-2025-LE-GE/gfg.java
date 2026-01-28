class Solution {
    // Function to convert a binary tree into its mirror tree.
    void mirror(Node node) {
        // Your code here
        dfs(node);
    }
    private static void dfs(Node node) {
        if (node == null) 
            return;
        dfs(node.left);
        dfs(node.right);
        
        Node temp = node.left;
        node.left = node.right;
        node.right = temp;
    }
}