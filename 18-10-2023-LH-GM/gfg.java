
class Solution {

    static int[] indeg;
    List<Integer> eventualSafeNodes(int V, List<List<Integer>> adj) {
        // Your code here
        List<Integer> ans = new ArrayList<>();
        List<List<Integer>> revAdj = new ArrayList<>();
        indeg = new int[V];
        Queue<Integer> q = new LinkedList<>();
        
        for(int i = 0; i < V; i++)
            revAdj.add(new ArrayList<>());
        
        
        for(int i = 0; i < adj.size(); i++) {
            for(int j = 0; j < adj.get(i).size(); j++) {
                revAdj.get(adj.get(i).get(j)).add(i);
            }
        }
        
        for(int i = 0; i < revAdj.size(); i++) {
            // System.out.println("\n--" + i);
            for(int j = 0; j < revAdj.get(i).size(); j++) {
                // System.out.print(revAdj.get(i).get(j) + " :: ");
                indeg[revAdj.get(i).get(j)]++;
            }
        }
        
        for(int i = 0; i < revAdj.size(); i++) {
            // System.out.print("::" + indeg[i]);
            if(indeg[i] == 0) {
                q.offer(i);
            }
        }
        
        while(!q.isEmpty()) {
            int newNode = q.poll();
            for(int i = 0; i < revAdj.get(newNode).size(); i++) {
                int neigh = revAdj.get(newNode).get(i);
                indeg[neigh]--;
                if(indeg[neigh] == 0)
                    q.offer(neigh);
            }
        }
        
        for(int i = 0; i < V; i++)
            if(indeg[i] == 0)
                ans.add(i);
        
        return ans;
    }
    
    private static void dfs(int node, List<List<Integer>> adj) {
        for(int i = 0; i < adj.get(node).size(); i++) {
            int neighbour = adj.get(node).get(i); 
            indeg[neighbour]--;
            if(indeg[neighbour] == 0)
                dfs(neighbour, adj);
        }
    }
}
