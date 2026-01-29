class Solution {
    public ArrayList<Integer> safeNodes(int V, int[][] edges) {
        // Code here
        // intuition - if we remove edges starting from nodes with 0 outgoing
        // edges, and use BFS, maybe will reach nodes which form cycle along with ones
        // who always have outgoing edges

        int[] outorder = new int[V];
        List<List<Integer>> adj = new ArrayList<>();
        ArrayList<Integer> ans = new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();

        for (int i = 0; i < V; i++)
            adj.add(new ArrayList<>());
        for(int[] edge : edges) {
            int from = edge[0], to = edge[1];
            adj.get(to).add(from);  // notice its not adj.from = to
            outorder[from]++;
        }
        for(int i = 0; i < V; i++)
            if (outorder[i] == 0)
                q.offer(i);
        while(!q.isEmpty()) {
            int currNode = q.poll();
            for(Integer neigh : adj.get(currNode)) {
                outorder[neigh]--;
                if (outorder[neigh] == 0)   q.offer(neigh);
            }
        }
        for (int i = 0; i < outorder.length; i++)
            if (outorder[i] == 0)
                ans.add(i);
        return ans;

    }
}