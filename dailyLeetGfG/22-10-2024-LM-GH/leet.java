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
    public long kthLargestLevelSum(TreeNode root, int k) {
        Queue<TreeNode> q = new LinkedList<>();
        List<Long> arr = new ArrayList<>();
        q.offer(root);
        while(!q.isEmpty()) {
            int n = q.size();
            long currLevelSum = 0;
            while(n-- > 0) {
                TreeNode curr = q.poll();
                currLevelSum += curr.val;
                if(curr.left != null)
                    q.offer(curr.left);
                if(curr.right != null)
                    q.offer(curr.right);
            }
            arr.add(currLevelSum);
        }
        int m = arr.size();
        if(m < k)  return -1;
        Collections.sort(arr);
        for(int i = m - 1; i >= 0; i--) {
            if(m - i == k)
                return arr.get(i);
        }
        return -1;
    }
}