
/*
class Node {
    int data;
    Node left;
    Node right;
    Node(int data) {
        this.data = data;
        left = null;
        right = null;
    }
}
*/

class Solution {
    private int ans;
    int diameter(Node root) {
        // Your code here
        ans = 0;
        solve(root);
        return ans;
    }
    private int solve(Node root) {
        if (root == null)   return 0;
        int left = solve(root.left);
        int right = solve(root.right);
        ans = Math.max(ans, left + right);
        return Math.max(left, right) + 1;
    }
}