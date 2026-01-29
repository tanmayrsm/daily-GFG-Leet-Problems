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
    public TreeNode balanceBST(TreeNode root) {
        arr = new ArrayList<>();
        dfs(root);
        Collections.sort(arr);
        return build(0, arr.size() - 1);
    }   
    private static void dfs(TreeNode root) {
        if (root == null)   return;
        dfs(root.left);
        arr.add(root.val);
        dfs(root.right);
    }
    private static TreeNode build(int l, int r) {
        if (l > r)  return null;
        int mid = (l + r) / 2;
        TreeNode newer = new TreeNode(arr.get(mid));
        newer.left = build(l, mid - 1);
        newer.right = build(mid + 1, r);
        return newer;
    }
}