/*
class Node {
    int data;
    Node left, right;
    Node(int x) {
        data = x;
        left = right = null;
    }
}
*/
class Solution {
    Node left, right;
    public ArrayList<Node> findPreSuc(Node root, int key) {
        // code here
        ArrayList<Node> ans = new ArrayList<>();
        solve(root, key);
        ans.add(left == null ? null : left);
        ans.add(right == null ? null : right);
        return ans;
    }
    private void solve(Node root, int key) {
        if (root == null)   return;
        if (left != null && root.data > left.data && root.data < key) {
            left = root;
        } else if (left == null && root.data < key) {
            left = root;
        }
        if (right != null && root.data < right.data && root.data > key) {
            right = root;
        } else if (right == null && root.data > key) {
            right = root;
        }
        solve(root.left, key);
        solve(root.right, key);
    }
}