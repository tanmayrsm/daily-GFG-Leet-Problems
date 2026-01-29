
//User function Template for Java

class Solution
{
    private static Stack<Integer> order;
    //Function to find number of strongly connected components in the graph.

    public int kosaraju(int V, ArrayList<ArrayList<Integer>> adj)
    {
        //code here
        boolean[] vis;
        int ans = 0;
        order = new Stack<>();

        // step 1 -> make list of nodes, as per their finishing time
        // i.e. - node at last in graph (having no outgoing edge) will be first one in this list
        // and the source node would be last
        vis = new boolean[V];
        for(int i = 0; i < V; i++)
            dfs1(adj, i, vis);

        // step 2 -> reverse all edges
        ArrayList<ArrayList<Integer>> reverse = new ArrayList();
        for(int i = 0; i < V; i++)
            reverse.add(new ArrayList<>());
        
        for(int i = 0; i < V; i++)
            for(int x : adj.get(i))
                reverse.get(x).add(i);

        // step 3 -> do dfs on each node in reverse list, as per queue order
        vis = new boolean[V];
        while(!order.isEmpty()) {
            int currNode = order.pop();
            if(!vis[currNode]) {
                dfs2(reverse, currNode, vis);   // in this func, u can also take node, add them in list, if qs asks to get all SOCs in list
                ans++;
            }
        }

        return ans;
    }

    private static void dfs1(ArrayList<ArrayList<Integer>> adj, int node, boolean[] vis) {
        vis[node] = true;
        for(int x : adj.get(node)) {
            if(!vis[x])
                dfs1(adj, x, vis);
        }
        order.push(node);
    }

    private static void dfs2(ArrayList<ArrayList<Integer>> adj, int node, boolean[] vis) {
        vis[node] = true;
        for(int x : adj.get(node)) {
            if(!vis[x])
                dfs2(adj, x, vis);
        }
    }
}