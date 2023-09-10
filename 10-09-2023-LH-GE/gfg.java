
class Solution {
    // catch - Insert node only at leaves, not in between two nodes 
    private static Node ans;
    // Function to insert a node in a BST.
    Node insert(Node root, int key) {
        // your code here
        inorder(root, key);
        return root;
    }
    private static void inorder(Node root, int key) {
        if(root == null)    return;
        if(root.data == key)  {
            return;
        }
        if(root.data < key) {
            // System.out.println(root.data + " :: " + key);
            if(root.right == null) {
                root.right = new Node(key);
            } else 
                inorder(root.right, key);
        }
        else if(root.data > key) {
            // System.out.println(root.data + " :: " + key);
            if(root.left == null) {
                root.left = new Node(key);
            } else 
                inorder(root.left, key);
        }
    }
}