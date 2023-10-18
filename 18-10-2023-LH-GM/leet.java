class Solution {// referred soln
    public int minimumTime(int n, int[][] relations, int[] time) {

        List<List<Integer>> graph = new ArrayList<>();
        for(int i=0;i<n;i++) {
            graph.add(new ArrayList<>());
        }

        int[] in = new int[n];
        for(int[] rel: relations) {
            graph.get(rel[0]-1).add(rel[1]-1);
            in[rel[1]-1]++;
        }

        Queue<Integer> q = new LinkedList<>();
        for(int i=0;i<n;i++) {
            if(in[i] == 0) {
                q.add(i);
            }
        }

        int[] dist = new int[n];
        System.arraycopy(time, 0, dist, 0, n);
        while(!q.isEmpty()) {
            // pick all with indegree zero and find max time
            int size = q.size();
            for(int i=0;i<size;i++) {
                int poll = q.poll();
                // decrease adjacent indegree
                for(int adj: graph.get(poll)) {
                    dist[adj] = Math.max(dist[adj], dist[poll] + time[adj]);
                    in[adj]--;
                    if(in[adj] == 0) {
                        q.add(adj);
                    }
                }
            }
        }

        return Arrays.stream(dist).max().orElse(0);
    }
}