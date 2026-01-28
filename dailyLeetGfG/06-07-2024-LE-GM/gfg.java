
// User function Template for Java

/*
class Node {
    int data;
    Node left, right,next;

    public Node(int data){
        this.data = data;
    }
}
*/
class Solution {
    private static Node prev ;
    public void populateNext(Node root) {
        // code here
        prev = null;
        dfs(root);
    }
    private static void dfs(Node root) {
        if (root == null)   return;
        dfs(root.left);
        //
        if (prev == null)   prev = root;
        else {
            prev.next = root;
            prev = root;
        }
        dfs(root.right);
    }
}
//    2
//  1   3
// 4   5

// inorder => 4 1 2 5 3