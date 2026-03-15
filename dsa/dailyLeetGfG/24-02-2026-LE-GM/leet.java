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
    public int sumRootToLeaf(TreeNode root) {
        return solve(root, 0);
    }
    private int solve(TreeNode root, int no) {
        if(root == null)    return 0;
        int val = (no << 1) | root.val;

        // if leaf, return the built number
        if (root.left == null && root.right == null) {
            return val;
        }
        int left = solve(root.left, val);
        int right = solve(root.right, val);
        return left + right;
    }
}