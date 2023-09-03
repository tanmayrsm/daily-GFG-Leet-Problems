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
        int maxAns = Integer.MIN_VALUE;
        int maxLevel = 1;
        int currLevel = 1;
        q.offer(root);
        while(!q.isEmpty()) {
            int n = q.size();
            int levelSum = 0;
            while(n-- > 0) {
                TreeNode r = q.poll();
                levelSum += r.val;
                if(r.left != null)
                    q.offer(r.left);
                if(r.right != null)
                    q.offer(r.right);
            }
            if(levelSum > maxAns) {
                maxAns = levelSum;
                maxLevel = currLevel;
            }
            currLevel++;
        }
        return maxLevel;
    }
}