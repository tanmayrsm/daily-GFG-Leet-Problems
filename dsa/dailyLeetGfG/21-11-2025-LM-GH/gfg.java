class Solution {
    public int shortestPath(int V, int a, int b, int[][] edges) {
        // Build adjacency list
        @SuppressWarnings("unchecked")
        List<int[]>[] adj = (List<int[]>[]) new ArrayList[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], straight = edge[2], curved = edge[3];
            adj[u].add(new int[]{v, straight, curved});
            adj[v].add(new int[]{u, straight, curved});
        }

        // dist[node][curvesUsed] = minimum distance
        int[][] dist = new int[V][2];
        for (int i = 0; i < V; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        // PQ: [node, cost, curvesUsed]
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> Integer.compare(x[1], y[1]));

        pq.offer(new int[]{a, 0, 0});
        dist[a][0] = 0;

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int node = curr[0], cost = curr[1], curvesUsed = curr[2];

            // If we've already found a better path to this state, skip
            if (cost > dist[node][curvesUsed]) {
                continue;
            }

            // Explore neighbors
            for (int[] neighbor : adj[node]) {
                int nextNode = neighbor[0];
                int straightCost = neighbor[1];
                int curvedCost = neighbor[2];

                // Try taking straight edge
                int newCost = cost + straightCost;
                if (newCost < dist[nextNode][curvesUsed]) {
                    dist[nextNode][curvesUsed] = newCost;
                    pq.offer(new int[]{nextNode, newCost, curvesUsed});
                }

                // Try taking curved edge (only if we haven't used a curve yet)
                if (curvesUsed == 0) {
                    newCost = cost + curvedCost;
                    if (newCost < dist[nextNode][1]) {
                        dist[nextNode][1] = newCost;
                        pq.offer(new int[]{nextNode, newCost, 1});
                    }
                }
            }
        }

        // Return minimum of reaching b with 0 or 1 curves used
        int result = Math.min(dist[b][0], dist[b][1]);
        return result == Integer.MAX_VALUE ? -1 : result;
    }
}