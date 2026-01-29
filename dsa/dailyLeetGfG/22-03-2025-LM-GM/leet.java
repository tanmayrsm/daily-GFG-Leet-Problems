import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    boolean[] vis ;
    public int countCompleteComponents(int n, int[][] edges) {
        vis = new boolean[n];
        int[] indeg = new int[n];
        int ans = 0;
        Map<Integer, List<Integer>> adj = new HashMap<>();
        for(int[] edge : edges) {
            int from = edge[0], to = edge[1];
            if(!adj.containsKey(from))  
                adj.put(from, new ArrayList<>());
            if(!adj.containsKey(to))  
                adj.put(to, new ArrayList<>());
            adj.get(from).add(to);
            adj.get(to).add(from);
            indeg[from]++;
            indeg[to]++;
        }
        for (int i = 0; i < n; i++) {
            if (!vis[i]) {
                Boolean isComplete = true;
                List<Integer> allVertex = new ArrayList<>();
                getAllVertexInComponent(i, adj, allVertex);
                int totalVertexInComponent = allVertex.size();
                for(Integer vertex : allVertex) {
                    if (indeg[vertex] != totalVertexInComponent - 1) {
                        isComplete = false;
                        break;
                    }
                }
                if (isComplete)
                    ans++;
            }
        }
        return ans;
    }

    private void getAllVertexInComponent(int vertex, Map<Integer, List<Integer>> adj, List<Integer> allVertex) {
        if(vis[vertex]) return;
        vis[vertex] = true;
        allVertex.add(vertex);
        if(adj.get(vertex) != null && !adj.get(vertex).isEmpty()) {
            for(Integer neighbot : adj.get(vertex)) {
                if (!vis[neighbot]) {
                    getAllVertexInComponent(neighbot, adj, allVertex);
                }
            }
        }
    }
}
