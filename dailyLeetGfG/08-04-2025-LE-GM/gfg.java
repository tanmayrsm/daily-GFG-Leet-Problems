class Solution {
    public boolean isBridge(int V, int[][] edges, int c, int d) {
        // code here
        List<List<Integer>> adj = new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[V];

        for (int i = 0; i < V; i++)
            adj.add(new ArrayList<>());
        for (int[] edge : edges) {
            int from = edge[0], to = edge[1];
            if ((c == from && d == to ) || (d == from && c == to))
                continue;
            adj.get(from).add(to);
            adj.get(to).add(from);
        }
        
        q.add(c);
        visited[c] = true;
        while (!q.isEmpty()) {
            int node = q.poll();
            for (int neighbor : adj.get(node)) {
                if (neighbor == d)
                    return false;
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    q.add(neighbor);
                }
            }
        }

        return true;
    }
}