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
    int ans;
public int getMinimumDifference(TreeNode root) {
    ans = Integer.MAX_VALUE;
    PriorityQueue<Integer> pq = new PriorityQueue<>();
    dfs(root, pq);
    while(!pq.isEmpty()) {
        int first = pq.poll();
        if(pq.size() > 0)
            ans = Math.min(ans, Math.abs(first - pq.peek()));
    }
    return ans;
}
private static void dfs(TreeNode root, PriorityQueue<Integer> pq){
    if(root == null)    return;
    pq.offer(root.val);
    dfs(root.left, pq);
    dfs(root.right, pq);
}
}