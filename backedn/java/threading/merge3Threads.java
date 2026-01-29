class Solution {
    boolean[] vis;
    int totalSum, ans;
    public int maxKDivisibleComponents(int n, int[][] edges, int[] values, int k) {
        List<List<Integer>> adj = new ArrayList<>();
        vis = new boolean[n];
        int root = 0; ans = 0;
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
            totalSum += values[i];
        }
        for (int[] edge : edges) {
            int from = edge[0], to = edge[1];
            adj.get(from).add(to);
            adj.get(to).add(from);
        }
        solve(0, adj, values, k);
        return ans;
    }
    private long solve(int root, List<List<Integer>> adj, int[] values, int k) {
        vis[root] = true;
        long childSum = 0;
        for (Integer neigh : adj.get(root)) {
            if (neigh >= 0 && !vis[neigh]) {
                childSum += solve(neigh, adj, values, k);
            }
        }
        long currSum = childSum + values[root];
        if (currSum % k == 0) {
            ans++;
            return 0;
        }
        return values[root] + childSum;
    }
}