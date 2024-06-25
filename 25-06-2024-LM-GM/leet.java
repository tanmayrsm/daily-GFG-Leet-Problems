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
    private static List<Integer> arr;
    private static int index;
    public TreeNode bstToGst(TreeNode root) {
        arr = new ArrayList<>();
        dfs(root);
        int n = arr.size();
        for(int i = n - 2; i >= 0; i--) {
            arr.set(i, arr.get(i) + arr.get(i + 1));
        }
        index = 0;
        setVal(root);
        return root;
    }
    private static void dfs(TreeNode root) {
        if (root == null)   return ;
        dfs(root.left);
        arr.add(root.val);
        dfs(root.right);
    }
    private static void setVal(TreeNode root) {
        if (root == null)   return;
        setVal(root.left);
        root.val = arr.get(index++);
        setVal(root.right);
    }

}