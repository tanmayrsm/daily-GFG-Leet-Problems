
class Solution
{
    static int[] findOrder(int n, int m, ArrayList<ArrayList<Integer>> arr) 
    {
        // add your code here
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();
        int[] ans = new int[n];
        int[] indegree = new int[n];
        int indx = 0;
        
        for(int i = 0; i < n; i++)
            adj.add(new ArrayList<>());
            
        for(ArrayList<Integer> x : arr) {
            int from = x.get(1);
            int to = x.get(0);
            adj.get(from).add(to);
            indegree[to]++;
        }
        
        for(int i = 0; i < n; i++) {
            if(indegree[i] == 0)    
                q.offer(i);
        }
        
        // bfs    
        while(!q.isEmpty()) {
            int node = q.poll();
            for(Integer x : adj.get(node)) {
                indegree[x]--;
                if(indegree[x] == 0)
                    q.offer(x);
            }
            ans[indx++] = node;
        }
        
        return indx == n ? ans : new int[0];
    }
}