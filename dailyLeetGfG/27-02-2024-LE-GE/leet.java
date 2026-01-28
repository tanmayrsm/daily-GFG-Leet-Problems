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
    private static int ans;
    public int diameterOfBinaryTree(TreeNode root) {
        ans = 0;
        dfs(root);
        return ans;
    }
    private static int dfs(TreeNode root) {
        if(root == null)    return 0;
        int l = 0, r = 0;
        if(root.left != null)
            l = dfs(root.left);
        if(root.right != null)
            r = dfs(root.right);
        ans = Math.max(ans, l + r);
        return 1 + Math.max(l, r);
    }
}