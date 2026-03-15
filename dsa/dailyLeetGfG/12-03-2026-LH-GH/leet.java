class Solution {
    class DSU {
        int[] parent;
        int[] size;

        DSU(int n) {
            parent = new int[n];
            size = new int[n];
            Arrays.fill(size, 1);
            for (int i = 0; i < n; i++)
                parent[i] = i;
        }

        int findUParent(int node) {
            if (parent[node] == node)
                return node;
            return parent[node] = findUParent(parent[node]);
        }

        void union(int u, int v) {
            int pU = findUParent(u);
            int pV = findUParent(v);
            if (pU == pV) return;

            if (size[pU] > size[pV]) {
                size[pU] += size[pV];
                parent[pV] = pU;
            } else {
                size[pV] += size[pU];
                parent[pU] = pV;
            }
        }
    }

    boolean isConnected(DSU dsu, int n) {
        int root = dsu.findUParent(0);
        for (int i = 1; i < n; i++) {
            if (dsu.findUParent(i) != root) return false;
        }
        return true;
    }

    public int maxStability(int n, int[][] edges, int k) {
        List<int[]> optionalEdges = new ArrayList<>();
        int minEdgeS = Integer.MAX_VALUE;
        DSU dsu = new DSU(n);

        // 1. Process Mandatory Edges
        for (int[] edge : edges) {
            if (edge[3] == 0) {
                optionalEdges.add(edge);
            } else {
                int u = edge[0], v = edge[1], s = edge[2];
                if (dsu.findUParent(u) == dsu.findUParent(v)) return -1; // Cycle in mandatory
                dsu.union(u, v);
                minEdgeS = Math.min(minEdgeS, s);
            }
        }

        // 2. Process Optional Edges (Greedy - Higher stability first)
        List<int[]> optionalTakenEdges = new ArrayList<>();
        Collections.sort(optionalEdges, (a, b) -> b[2] - a[2]);

        for (int[] edge : optionalEdges) {
            int u = edge[0], v = edge[1];
            if (dsu.findUParent(u) != dsu.findUParent(v)) {
                dsu.union(u, v);
                optionalTakenEdges.add(edge);
            }
        }

        // 3. Connectivity Check
        if (!isConnected(dsu, n)) return -1;

        // 4. Apply K-upgrades to the weakest chosen optional edges
        // optionalTakenEdges are currently sorted Desc, so the weakest are at the end.
        Collections.sort(optionalTakenEdges, (a, b) -> a[2] - b[2]);
        for (int i = 0; i < Math.min(k, optionalTakenEdges.size()); i++) {
            optionalTakenEdges.get(i)[2] *= 2;
        }

        // 5. Calculate final global minimum stability
        for (int[] edge : optionalTakenEdges) {
            minEdgeS = Math.min(minEdgeS, edge[2]);
        }

        return minEdgeS == Integer.MAX_VALUE ? -1 : minEdgeS;
    }
}