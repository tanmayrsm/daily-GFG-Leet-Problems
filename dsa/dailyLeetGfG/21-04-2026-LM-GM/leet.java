class Solution {
    boolean[] vis;
    public int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {
        List<List<Integer>> adj = new ArrayList<>();
        int n = source.length, ans = 0;
        vis = new boolean[n];
        for(int i = 0; i < n; i++)
            adj.add(new ArrayList<>());
        for(int[] x: allowedSwaps) {
            int from = x[0], to = x[1];
            adj.get(from).add(to);
            adj.get(to).add(from);
        }
        for(int i = 0; i < n; i++) {
            if(!vis[i]) {
                ans += dfs(i, adj, n, source, target);
            }
        }
        return ans;
    }
    private int dfs(int node, List<List<Integer>> adj, int n, int[] src, int[] tgt) {
        Map<Integer, Integer> s = new HashMap<>();
        Map<Integer, Integer> t = new HashMap<>();
        int ans = 0;
        vis[node] = true;
        solve(adj, node, s, t, n, src, tgt);
        for (Map.Entry<Integer, Integer> x : s.entrySet()) {
            int k = x.getKey(), v = x.getValue();
            if (t.containsKey(k)) {
                int tv = t.get(k);
                int reduce = Math.min(tv, v);
                t.put(k, tv - reduce);
            }
        }
        for (Map.Entry<Integer, Integer> x : t.entrySet()) {
            ans += x.getValue();
        }
        return ans;
    }

    private void solve(List<List<Integer>> adj, int curr, Map<Integer, Integer> s, Map<Integer, Integer> t, int n,
                       int[] src, int[] tgt) {

        s.put(src[curr], s.getOrDefault(src[curr], 0) + 1);
        t.put(tgt[curr], t.getOrDefault(tgt[curr], 0) + 1);

        for(Integer ch : adj.get(curr)) {
            if(!vis[ch]) {
                vis[ch] = true;
                solve(adj, ch, s,t,n, src, tgt);
            }
        }
    }
}