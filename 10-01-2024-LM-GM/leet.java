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
    private static int ans;
    public int amountOfTime(TreeNode root, int start) {
        Map<Integer, List<Integer>> adj = new HashMap<>();
        ans = 0;
        Set<Integer> vis = new HashSet<>();
        fillAdj(root, adj);

        dfs(adj, start, vis, 0);

        return ans;
    }
    private static void fillAdj(TreeNode root, Map<Integer, List<Integer>> adj) {
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while(!q.isEmpty()) {
            int n = q.size();
            while(n-- > 0) {
                TreeNode curr = q.poll();
                if(curr.left != null) {
                    fillNode(curr.val, curr.left.val, adj);
                    q.offer(curr.left);
                }
                if(curr.right != null) {
                    fillNode(curr.val, curr.right.val, adj);
                    q.offer(curr.right);
                }
            }
        }
    }

    private static void dfs(Map<Integer, List<Integer>> adj, int val, Set<Integer> vis, int currDist) {
        ans = Math.max(ans, currDist);
        vis.add(val);
        if(adj.get(val) != null)
            for(Integer x : adj.get(val)) {
                if(!vis.contains(x)) {
                    dfs(adj, x, vis, currDist + 1);
                }    
            }
    }

    private static void fillNode(int x, int y, Map<Integer, List<Integer>> adj) {
        if(adj.containsKey(x)) {
            adj.get(x).add(y);
        } else {
            List<Integer> newer = new ArrayList<>();
            newer.add(y);
            adj.put(x, newer);
        }

        if(adj.containsKey(y)) {
            adj.get(y).add(x);
        } else {
            List<Integer> newer = new ArrayList<>();
            newer.add(x);
           
            adj.put(y, newer);
        }
    }

}