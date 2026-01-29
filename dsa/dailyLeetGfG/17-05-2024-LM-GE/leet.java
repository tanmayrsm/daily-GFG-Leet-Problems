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
    public TreeNode removeLeafNodes(TreeNode root, int target) {
        boolean fin = solve(root, target);
        if(fin) return null;
        return root;
    }

    private static boolean solve(TreeNode root, int t) {
        if(root == null)    return false;
        if(root.left == null && root.right == null)
            return root.val == t;
        boolean left = solve(root.left, t);
        boolean right = solve(root.right, t);
        if(left)    root.left = null;
        if(right)   root.right = null;
        if((left || right) && (root.left == null && root.right == null))    return root.val == t;
        return false;
    }
}