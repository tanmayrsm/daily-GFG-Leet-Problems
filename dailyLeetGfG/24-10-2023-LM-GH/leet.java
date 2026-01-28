
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
    public List<Integer> largestValues(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        List<Integer> ans = new ArrayList<>();
        if(root == null)    return ans;
        q.offer(root);
        while(!q.isEmpty()) {
            int n = q.size();
            int maxi = Integer.MIN_VALUE;
            while(n-- > 0) {
                TreeNode x = q.poll();
                maxi = Math.max(maxi, x.val);
                if(x.left != null)
                    q.offer(x.left);
                if(x.right != null)
                    q.offer(x.right);   
            }
            ans.add(maxi);
        }
        return ans;
    }
}