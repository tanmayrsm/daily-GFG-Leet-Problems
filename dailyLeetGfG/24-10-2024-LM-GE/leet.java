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
    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        return dfs(root1, root2);
    }
    private boolean dfs(TreeNode root1, TreeNode root2) {
        if(root1 == null && root2 == null)  return true;
        if((root1 == null && root2 != null) || (root2 == null && root1 != null) || (root1.val != root2.val)) {
            return false;
        }
        if(!equalNodes(root1.left, root2.left)) {
            if (equalNodes(root1.left, root2.right)) {
                swap(root1);
                if(!equalNodes(root1.left, root2.left) || !equalNodes(root1.right, root2.right))
                    return false;
            } else    return false;
        }
        if(!equalNodes(root1.right, root2.right)) {
            if (equalNodes(root1.right, root2.left)) {
                swap(root1);
                if(!equalNodes(root1.left, root2.left) || !equalNodes(root1.right, root2.right))
                    return false;
            } else    return false;
        }
        

        boolean side1 = dfs(root1.left, root2.left);
        boolean side2 = dfs(root1.right, root2.right);
        return side1 && side2;
    }
    private boolean equalNodes(TreeNode r1, TreeNode r2) {
        if(r1 == null && r2 == null)    return true;
        if((r1 == null && r2 != null) || (r2 == null && r1 != null))    return false;
        return r1.val == r2.val;
    }
    private void swap(TreeNode root) {
        TreeNode l = root.left, r = root.right;
        root.left = r;
        root.right = l;
    }
}