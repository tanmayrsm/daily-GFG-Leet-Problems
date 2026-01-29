/*
class Node {
    int data;
    Node left;
    Node right;
    Node(int data){
        this.data = data;
        left=null;
        right=null;
    }
}
*/

class Solution {
    Node removekeys(Node root, int l, int r) {
        // code here
        return prune(root, l, r);
    }
    private Node prune(Node root, int l, int r) {
        if (root == null)   return null;
        if (root.data < l)
            return prune(root.right, l, r);
        if (root.data > r)
            return prune(root.left, l, r);
        root.left = prune(root.left, l, r);
        root.right = prune(root.right, l, r);
        return root;
    }
}