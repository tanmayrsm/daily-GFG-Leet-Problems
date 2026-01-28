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
    public boolean isEvenOddTree(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        boolean oddMustOccur = true;
        while(!q.isEmpty()) {
            int n = q.size();
            Integer prev = null;
            while(n-- > 0) {
                TreeNode x = q.poll();
                int curr = x.val;
                if((oddMustOccur && curr % 2 == 0) || (!oddMustOccur && curr % 2 != 0))  return false;
                if(prev != null && oddMustOccur && prev >= curr) return false;
                if(prev != null && !oddMustOccur && prev <= curr)   return false;
                prev = curr;
                if(x.left != null)  q.offer(x.left);
                if(x.right != null)  q.offer(x.right);
            }
            oddMustOccur = !oddMustOccur;
        }
        return true;
    }
}