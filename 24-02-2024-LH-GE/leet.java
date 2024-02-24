class Solution {
    private static boolean[] vis;
    public List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {
        Arrays.sort(meetings, (int[] a, int[] b) -> Integer.compare(a[2], b[2]));
        int m = meetings.length;
        vis = new boolean[n];
        List<Integer> ans = new ArrayList<>();
        vis[0] = true; vis[firstPerson] = true;

        for(int i = 0; i < m; ) {
            int j = i;
            Set<Integer> allNodes = new HashSet<>();
            Map<Integer, Set<Integer>> adj = new HashMap<>();
            while(j < m && meetings[j][2] == meetings[i][2]) {
                int n1 = meetings[j][0], n2 = meetings[j][1];
                if(adj.get(n1) == null)
                    adj.put(n1, new HashSet<>());
                if(adj.get(n2) == null)
                    adj.put(n2, new HashSet<>());
                adj.get(n1).add(n2);
                adj.get(n2).add(n1);
                
                if(vis[n1] || vis[n2]) {
                    allNodes.add(n1); 
                    allNodes.add(n2);
                }
                j++;
            }
            for(int node : allNodes) {
                dfs(node, adj);
            }
            i = Math.max(i + 1, j);
        }

        for(int i = 0; i < n; i++) {
            if(vis[i])
                ans.add(i);
        }

        return ans;
    }

    private static void dfs(int node, Map<Integer, Set<Integer>> adj) {
        if(vis[node])   return;
        vis[node] = true;
        for(int neigh : adj.get(node)) {
            dfs(neigh, adj);
        }
    }
}