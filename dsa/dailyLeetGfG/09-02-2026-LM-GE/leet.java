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
    List<TreeNode> arr;
    TreeNode ans;
    public TreeNode balanceBST(TreeNode root) {
        arr = new ArrayList<>();
        dfs(root);

        ans = null;
        int n = arr.size();
        dfsArr(0, n - 1,-1);
        return ans;
    }
    private TreeNode dfsArr(int l, int r, int prevMid) {
        int mid = (l + r) / 2;
        if(l > r)  return null;
        TreeNode curr = arr.get(mid);
        curr.left = null;
        curr.right = null;

        if(l == r)  return curr;
        if(ans == null)
            ans = curr;

        if(l >= r) {
            return curr;
        }
        TreeNode left = dfsArr(l, mid - 1, mid);
        TreeNode right = dfsArr(mid + 1, r, mid);
        curr.left = left;
        curr.right = right;
        return curr;
    }
    private void dfs(TreeNode root) {
        if(root == null)    return;
        dfs(root.left);
        arr.add(root);
        dfs(root.right);
    }
}