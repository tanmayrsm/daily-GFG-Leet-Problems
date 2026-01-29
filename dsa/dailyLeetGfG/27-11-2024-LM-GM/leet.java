class Solution {
    public int[] shortestDistanceAfterQueries(int n, int[][] queries) {
        // shortest path
        // idea is like dijk, if dist[something] > dist[curr] + 1, update it
        // and post that, update next nodes after that (use bfs for that)

        int[][] adj = new int[n][n];
        int[] dist = new int[n];
        int[] ans = new int[queries.length];
        int k = 0;

        for (int[] p : adj)
            Arrays.fill(p, -1);

        for (int i = 0; i < n-1; i++) {
            adj[i][i + 1] = 1;
            dist[i + 1] = i + 1;
        }

        for (int[] query : queries) {
            int from = query[0], to = query[1];
            adj[from][to] = 1;
            if (dist[to] > dist[from] + 1) {
                dist[to] = dist[from] + 1;
                // after this, we need to update dist for all nodes after 'to' node
                Queue<Integer> q = new LinkedList<>();
                q.offer(to);
                System.out.println();
                while (!q.isEmpty()) {
                    int source = q.poll();
                    for (int i = 0; i < n; i++) {
                        if (i != source && adj[source][i] == 1 && dist[i] > dist[source] + 1) {
                            dist[i] = dist[source] + 1;
                            q.offer(i);
                        }
                    }
                }
            }
            ans[k++] = dist[n - 1];
        }

        return ans;
    }
}