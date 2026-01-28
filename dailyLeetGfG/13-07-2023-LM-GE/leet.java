class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> adj = new HashMap<>();
        int[] indegree = new int[numCourses];
        Queue<Integer> q = new LinkedList<>();
        for(int[] x : prerequisites) {
            if(adj.get(x[0]) == null) {
                adj.put(x[0], new ArrayList<>());
            }
            adj.get(x[0]).add(x[1]);
            indegree[x[1]]++;
        }
        for(int i = 0; i < indegree.length; i++) {
            if(indegree[i] == 0)  q.offer(i);
        }
        while(!q.isEmpty()) {
            int n = q.size();
            while(n-- > 0) {
                int currNode = q.poll();
                List<Integer> neigh = adj.get(currNode);
                if(neigh != null && neigh.size() > 0) {
                    for(Integer x :  neigh) {
                        indegree[x]--;
                        if(indegree[x] == 0)
                            q.offer(x);
                    }
                }
            }
        }
        for(int x :  indegree) 
            if(x != 0)
                return false;
        return true;

    }
}