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
    public int maxLevelSum(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int maxo = Integer.MIN_VALUE, maxLevel = -1, currLevel = 1;
        while (!q.isEmpty()) {
            int n = q.size(), curr = 0;
            while (n-- > 0) {
                TreeNode polled = q.poll();
                curr += polled.val;
                if(polled.left != null) q.offer(polled.left);
                if(polled.right != null)    q.offer(polled.right);
            }
            if(curr > maxo) {
                maxo = curr;
                maxLevel = currLevel;
            }
            currLevel++;
        }
        return maxLevel;
    }
}