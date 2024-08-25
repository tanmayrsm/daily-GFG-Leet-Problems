import java.util.ArrayList;
import java.util.Stack;

import javax.swing.tree.TreeNode;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    private static List<Integer> ans;
    public List<Integer> postorderTraversal(TreeNode root) {
        // LRV
        ans = new ArrayList<>();
        Stack<TreeNode> st = new Stack<>();
        st.push(root);
        Node curr = root;
        while (!st.isEmpty()) {
            Node rem = st.peek();
            if (rem.left != null) {
                while (rem.left != null) {
                    st.push(rem.left);
                    rem = rem.left;
                }
            }
            if (rem.right != null) {
                st.push(rem.right);
            }
            
        }

        return ans;
    }
    private static void solve (TreeNode node) {
        if (node == null)   return;
        solve(node.left);
        solve(node.right);
        ans.add(node.val);
    }
}