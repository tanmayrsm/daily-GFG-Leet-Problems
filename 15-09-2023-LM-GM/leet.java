class Solution {
    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        int[][] adj = new int[n][n];
        for(int[] x : adj)  Arrays.fill(x, -1);

        for(int i = 0; i < n; i++) {
            for(int j = i + 1; j < n; j++) {
                int cost = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
                adj[i][j] = cost;
                adj[j][i] = cost;
            }
        }

        // MCST - PRIMS
        boolean[] vis = new boolean[n];
        int total = 0;
        // EXTRA - to get connected edges -
        // List<List<Integer>> conn = new ArrayList<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.valueOf(o1[0]).compareTo(o2[0]);
            }
        });

        // int[] = {cost, node, parent}
        pq.offer(new int[] {0, 0, -1});

        while(!pq.isEmpty()) {
            int[] first = pq.poll();
            int currCost = first[0], node = first[1], parent = first[2];
            if(!vis[node]) {
                int[] adjacency = adj[node];
                for(int i = 0; i < n; i++) {
                    if(adjacency[i] != -1) {    // not current node
                        int[] newSet = new int[] {adjacency[i], i, node};   // {cost, node, parent}
                        pq.offer(newSet);
                    }
                }
                vis[node] = true;
                total += currCost;
                // add {node, parent} to your MST LIST
                // conn.add(new ArrayList<>(node, parent))
            }
        }

        return total;

    }
}