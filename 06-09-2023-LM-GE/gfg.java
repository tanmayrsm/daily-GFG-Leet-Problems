
class Solution
{
    static void DFSUtil(ArrayList<ArrayList<Integer>> g,int v,boolean[] visited) {
        visited[v] = true;
        for(int x : g.get(v)) {
            if (!visited[x]) {
                DFSUtil(g, x, visited);
            }
        }
    }

    public int findMotherVertex(int V, ArrayList<ArrayList<Integer>>adj) {
        boolean[] visited = new boolean[V];
        int v = -1;
        for(int i = 0; i < V; i++) {
            if (!visited[i]){
                DFSUtil(adj, i, visited);
                v = i;
            }
        }
        boolean[] check = new boolean[V];
        DFSUtil(adj, v, check);
        for(boolean val : check) {
            if (!val) {
                return -1;
            }
        }
        return v;
    }
}