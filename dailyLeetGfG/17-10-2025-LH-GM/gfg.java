/*
class Node{
    int data;
    Node left;
    Node right;
    Node(int data){
        this.data = data;
        left=null;
        right=null;
    }
} */

class Solution {
    public static void transformTree(Node root) {
        // code here
        solve(root, 0);
    }
    private static int solve(Node root, int otherSum) {
        if (root == null)   return 0;
        int sumRight = solve(root.right, otherSum);
        int oldRootData = root.data;
        root.data = sumRight + otherSum;
        // System.out.println("change ::" + oldRootData + "::" + root.data + "::" + otherSum);
        int sumLeft = solve(root.left, oldRootData + sumRight + otherSum);
        return sumLeft + oldRootData + sumRight;
    }
}