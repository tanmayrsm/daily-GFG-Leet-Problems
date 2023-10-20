
class Solution{
    static ArrayList<ArrayList<Integer>> transitiveClosure(int N, int graph[][])
    {
        ArrayList<ArrayList<Integer>> transitive = new ArrayList<>();
        for (int i = 0; i < N; i++){
            ArrayList<Integer> temp = new ArrayList<>();
            for (int j = 0; j < N; j++) {
                temp.add(0);
            }
            transitive.add(temp);
        }
        
        
        for (int i = 0; i < N; i++){
            boolean[] visited = new boolean[N];
            
            ArrayList<Integer> pathFrom = new ArrayList<>();
            dfs(transitive, pathFrom, i, graph, visited);
            
            for (int node: pathFrom) {
                transitive.get(i).set(node, 1);
            }
        }
        
        return transitive;
        
    }
    
    
    static void dfs(ArrayList<ArrayList<Integer>> transitive, ArrayList<Integer> pathFrom, int node, int[][] graph, boolean[] visited) {
        visited[node] = true;
        pathFrom.add(node);
        
        for (int nbr = 0; nbr < graph[node].length; nbr++) {
            if (!visited[nbr] && graph[node][nbr] == 1)
                dfs(transitive, pathFrom, nbr, graph, visited);
        }
        
    }
}