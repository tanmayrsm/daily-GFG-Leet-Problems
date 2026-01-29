class Solution {
    private int[] parent;
    private int[] maxComponent;

    int components;
    private int find(int a) {
        if (parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }
    private void unite(int a, int b, int edgeCost) {
        int x = find(a), y = find(b);
        if (x != y) components--;
        else return;
        parent[y] = x;
        maxComponent[x] = Math.max(maxComponent[x], edgeCost);
    }
    public int minCost(int n, int[][] edges, int k) {
        components = n;
        parent = new int[n];
        maxComponent = new int[n];

        if (k == n) return 0;

        Arrays.sort(edges, (int[] a, int[] b) -> Integer.compare(a[2], b[2]));
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            maxComponent[i] = -1;
        }
        for (int i = 0; i < edges.length; i++) {
            unite(edges[i][0], edges[i][1], edges[i][2]);
            if (components <= k) {
                int maxer = 0;
                for (int j = 0; j < maxComponent.length; j++) {
                    if (parent[j] == j)
                        maxer = Math.max(maxer, maxComponent[j]);
                }
                return maxer;
            }
        }

        return 0;
    }
}