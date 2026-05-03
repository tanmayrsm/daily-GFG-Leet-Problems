class Solution {
    public int numBusesToDestination(int[][] routes, int source, int target) {
        int n = routes.length;
        List<Set<Integer>> adj = new ArrayList<>();
        boolean[] vis = new boolean[n];
        for(int i = 0; i < n; i++)  adj.add(new HashSet<>());
        Map<Integer, Set<Integer>> mp = new HashMap<>();    // node to mini node
        Map<Integer, Set<Integer>> mp2 = new HashMap<>();    // mino node to node

        for(int i = 0; i < routes.length; i++) {
            Set<Integer> st = new HashSet<>();
            for(int j = 0; j < routes[i].length; j++) {
                st.add(routes[i][j]);
                if(!mp2.containsKey(routes[i][j]))
                    mp2.put(routes[i][j], new HashSet<>());
                mp2.get(routes[i][j]).add(i);
            }
            mp.put(i, st);
        }

        for(Map.Entry<Integer, Set<Integer>> e : mp2.entrySet()) {
            Integer key = e.getKey();
            List<Integer> val = new ArrayList<>(e.getValue());
            int m = val.size();
            for(int i = 1; i < m; i++) {
                int from = val.get(i - 1), to = val.get(i);
                adj.get(from).add(to);
                adj.get(to).add(from);
            }
        }

        Queue<Integer> q = new LinkedList<>();  // q has nodes
        for(Integer node : mp2.get(source)) q.offer(node);
        int len = 1;
        while(!q.isEmpty()) {
            int m = q.size();
            while (m-- > 0) {
                int node = q.poll();
                if(mp.get(node).containsKey(target))    return len;
                for(int child : adj.get(node)) {
                    if(!vis[child])
                        q.offer(child);
                }
            }
            len++;
        }
        return -1;
    }
}