/*
class Node {
    int data;
    Node left, right;
    Node(int val){
        data = val;
        left = right = null;
    }
}
*/

class Solution {
    ArrayList<Integer> ans;
    public ArrayList<Integer> postOrder(Node root) {
        // code here
        ans = new ArrayList<>();
        solve(root);
        return ans;
    }
    private void solve(Node root) {
        if (root == null)   return;
        solve(root.left);
        solve(root.right);
        ans.add(root.data);
    }
}