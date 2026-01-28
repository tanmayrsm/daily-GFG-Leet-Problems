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
    public int rangeSumBST(TreeNode root, int low, int high) {
        ans = 0;
        dfs(root, low, high);
        return ans;
    }
    private static void dfs(TreeNode root, int l, int r) {
        if(root == null)    return;
        if(root.val >= l && root.val <= r)  ans += root.val;

        if(root.val >= l)
            dfs(root.left, l, r);
        if(root.val < r)
            dfs(root.right, l, r);
    }
}