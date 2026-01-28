class Solution {
    static int ans;
    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        Map<Integer, List<Integer>> adj = new HashMap<>();
        Queue<Integer> q = new LinkedList<>();
        ans = 0;
        for(int i = 0; i < manager.length; i++) {
            int x = manager[i];
            if(x == -1)
                continue;
            if(adj.get(x) == null)
                adj.put(x, new ArrayList<>());
            adj.get(x).add(i);
        }
        dfs(headID, adj, informTime[headID], informTime);
        return ans;
    }
    private static void dfs(int node, Map<Integer, List<Integer>> adj, int currSum, int[] informTime) {
        if(adj.get(node) == null) {
            ans = Math.max(ans, currSum);
            return;
        }
        for(int x : adj.get(node)) {
            dfs(x, adj, currSum + informTime[x], informTime);
        }
    }
}