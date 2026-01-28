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
    public int minDepth(TreeNode root) {
        if(root == null)    return 0;
        ans = Integer.MAX_VALUE;
        dfs(root, 1);
        return ans;
    }
    private static void dfs(TreeNode root, int level) {
        if(root == null)
            return ;
        if(root.left == null && root.right == null) {
            ans = Math.min(ans, level);
            return ;
        }
        dfs(root.left, level + 1);
        dfs(root.right, level + 1);
    }
}