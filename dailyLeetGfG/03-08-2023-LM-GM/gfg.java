
//User function Template for Java
class Solution {

	public int[] shortestPath(int N,int M, int[][] edges) {
		//Code here
		int[] cost = new int[N];
		boolean[] vis = new boolean[N];
		
		Map<Integer, Map<Integer, Integer>> adj = new HashMap<>();
		for(int i = 0; i < N; i++)
		    adj.put(i, new HashMap<>());
		    
		for(int i = 0; i < M; i++) {
			adj.get(edges[i][0]).put(edges[i][1], edges[i][2]);
		}

		Arrays.fill(cost, Integer.MAX_VALUE);

		cost[0] = 0;
		Queue<Integer> q = new LinkedList<>();
		q.offer(0);
		while(!q.isEmpty()) {
			int n = q.size();
			while(n-- > 0) {
				int node = q.poll();
					vis[node] = true;
					if(adj.get(node).size() > 0)
    					for(Map.Entry<Integer, Integer> x : adj.get(node).entrySet()) {
    						int neighbour = x.getKey();
    						int c = x.getValue();
    				        if(c + cost[node] < cost[neighbour])
    							q.offer(neighbour);
    						cost[neighbour] = Math.min(cost[neighbour], c + cost[node]);
    					}
			}
		}

        for(int i = 0; i < N; i++)
            if(cost[i] == Integer.MAX_VALUE)
                cost[i] = -1;
		return cost;
	}
}