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
    class Pair {
        int x, y, val;
        Pair(int row, int col, int value) {
            this.x = row;
            this.y = col;
            this.val = value;
        }
    }
    PriorityQueue<Pair> pq;
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        int last = Integer.MIN_VALUE, currSize = 0;

        // priority queue will take care of order of elements to be added
        pq = new PriorityQueue<>((Pair a, Pair b) -> {
            if (a.y == b.y) {
                if (a.x == b.x) return Integer.compare(a.val, b.val);
                return Integer.compare(a.x, b.x);
            }
            return Integer.compare(a.y, b.y);
        });

        dfs(root, 0, 0);

        while(!pq.isEmpty()) {
            Pair curr = pq.poll();
            if (curr.y == last && last != Integer.MIN_VALUE) {
                ans.get(currSize - 1).add(curr.val);
            } else {
                List<Integer> newer = new ArrayList<>();
                newer.add(curr.val);
                ans.add(newer);
                currSize++;
            }
            // need last to check if popped element could be added in last one, or new to be made
            last = curr.y;
        }
        return ans;
    }
    private void dfs(TreeNode root, int x, int y) {
        if (root == null)   return;
        dfs(root.left, x + 1, y - 1);
        dfs(root.right, x + 1, y + 1);
        pq.offer(new Pair(x, y, root.val));
    }
}