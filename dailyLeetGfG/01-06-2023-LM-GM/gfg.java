
class Solution
{
    //Function to return list containing vertices in Topological order. 
    static int[] topoSort(int V, ArrayList<ArrayList<Integer>> adj) 
    {
        // add your code here
        // approach - similar to find cycle in grapg using indegree method
        int ans[]= new int[V];
        int ind=0;
        
        Queue<Integer> q = new LinkedList<>();
        int indegree[] = new int[V];
        for( int i=0; i<V;i++)
        {
            for(int x : adj.get(i))
            indegree[x]++;
        }
        
        for( int i=0;i<V;i++)
        {
            if(indegree[i]==0)
            q.add(i);
        }
        
        while(!q.isEmpty())
        {
            int u =q.poll();
            ans[ind++]=u;
            
            for( int v:  adj.get(u))
            if(--indegree[v]==0)
            q.add(v);
        }
        return ans;
    }
}
