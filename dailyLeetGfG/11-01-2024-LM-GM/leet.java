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
    public int maxAncestorDiff(TreeNode root) {
        ans = Integer.MIN_VALUE;
        dfs(root);
        return ans;
    }
    private static int[] dfs(TreeNode x) {
        if(x == null)   return new int[2];

        int maxo = x.val, mini = x.val;

        if(x.left != null) {
            int[] left = dfs(x.left);
            maxo = Math.max(maxo, Math.max(left[0], left[1]));
            mini = Math.min(mini, Math.min(left[0], left[1]));
            
        }
        if(x.right != null) {
            int[] right = dfs(x.right);
            maxo = Math.max(maxo, Math.max(right[0], right[1]));
            mini = Math.min(mini, Math.min(right[0], right[1]));
        }
        ans = Math.max(ans, Math.max(Math.abs(x.val - mini), Math.abs(x.val - maxo)));
        return new int[] {maxo, mini};
    }
}