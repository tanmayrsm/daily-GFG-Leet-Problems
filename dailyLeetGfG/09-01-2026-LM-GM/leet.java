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
    TreeNode lca;
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        lca = null;
        TreeNode l = null, r = null;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int n = q.size();
            TreeNode ll = null, rr = null;

            while (n-- > 0) {
                TreeNode h = q.poll();
                if(ll == null) {
                    ll = h;
                }
                rr = h;
                if(h.left != null)  q.offer(h.left);
                if(h.right != null)  q.offer(h.right);
            }
            l = ll;
            r = rr;
        }
        getLCA(l, r);
        return lca;
    }
    private int[] getLCA(TreeNode root, int val1, int val2) {
        if(root == null) return new int[] {-1, -1};
        int[] l = getLCA(root.left, val1, val2);
        int[] r = getLCA(root.right, val1, val2);
        
        int[] res = new int[] {-1, -1};
        if(root.val == val1 || l[0] == val1 || r[0] == val1)
            res[0] = val1;
        if(root.val == val2 || l[1] == val2 || r[1] == val2)
            res[1] = val2;
        if(lca == null && res[0] == val1 && res[1] == val2)
            lca = root;
        return res;
    }   
}