
// User function Template for Java

class Solution {
    public boolean isEularCircuitExist(int v, ArrayList<ArrayList<Integer>> adj) {
        // Code here
        // euler path is something, which visits all edges, and stops at started edge
        // one observation - if indegree of every node is even, euler path is possible
        int[] indeg = new int[v];
        int n = adj.size();
        for(int i = 0; i < n; i++)
            if (adj.get(i).size() % 2 == 1)
                return false;
            
        
        return true;
    }
}