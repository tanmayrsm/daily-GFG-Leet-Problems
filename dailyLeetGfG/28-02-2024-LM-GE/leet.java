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
    public int findBottomLeftValue(TreeNode root) {
        // bfs
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int last = -1;
        while(!q.isEmpty()) {
            int n = q.size();
            Integer first = null;
            while(n-- > 0) {
                TreeNode x = q.poll();
                if(first == null) {
                    first = x.val;
                    last = first;
                }
                if(x.left != null)  q.offer(x.left);
                if(x.right != null)  q.offer(x.right);
            }
        }
        return last;
    }
}