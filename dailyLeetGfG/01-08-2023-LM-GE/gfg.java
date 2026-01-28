
class Solution {
    // Function to return a list containing the DFS traversal of the graph.
    private static ArrayList<Integer> ans;
    public ArrayList<Integer> dfsOfGraph(int V, ArrayList<ArrayList<Integer>> adj) {
        // Code here
        ans = new ArrayList<>();
        boolean[] vis = new boolean[V];
        dfs(adj, vis, 0);
        return ans;
    }
    
    private static void dfs(ArrayList<ArrayList<Integer>> adj, boolean[] vis, int currNode) {
        ans.add(currNode);
        vis[currNode] = true;
        for(Integer x : adj.get(currNode)) {
            if(!vis[x])
                dfs(adj, vis, x);
        }
    }
}