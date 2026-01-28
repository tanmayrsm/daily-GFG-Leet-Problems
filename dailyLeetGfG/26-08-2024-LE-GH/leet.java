/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

import java.util.ArrayList;

class Solution {
    private static List<Integer> ans;
    public List<Integer> postorder(Node root) {
        ans = new ArrayList<>();
        solve(root);
        return ans;
    }
    private static void solve (Node root) {
        if (root == null)   return;
        for (Node child : root.children) {
            solve(child);
        }
        ans.add(root.val);
    }
}