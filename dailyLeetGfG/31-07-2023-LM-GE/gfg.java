
class Solution {
    // Function to return Breadth First Traversal of given graph.
    public ArrayList<Integer> bfsOfGraph(int V, ArrayList<ArrayList<Integer>> adj) {
        // Code here
        Queue<Integer> q = new LinkedList<>();
        ArrayList<Integer> ans = new ArrayList<>();
        Set<Integer> vis = new HashSet<>();
        q.add(0);
        while(!q.isEmpty()) {
            int n = q.size();
            while(n-- > 0) {
                int node = q.poll();
                ans.add(node);
                for(Integer x :  adj.get(node)) {
                    if(!vis.contains(x)) {
                        q.offer(x);
                        vis.add(x);
                    }
                }
            }
        }
        return ans;
    }
}