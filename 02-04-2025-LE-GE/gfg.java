import java.util.LinkedList;
import java.util.Queue;

class Solution {
    // Function to return Breadth First Search Traversal of given graph.
    public ArrayList<Integer> bfs(ArrayList<ArrayList<Integer>> adj) {
        // code here
        int n = adj.size();
        boolean[] vis = new boolean[n];
        ArrayList<Integer> ans = new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        vis[0] = true;
        while(!q.isEmpty()){
            int node = q.poll();
            ans.add(node);
            for(int x : adj.get(node)){
                if(!vis[x]) {
                    q.add(x);
                    vis[x] = true;
                }
            }
        }   
        return ans;
    }
}