class Solution {
    static boolean[] vis;
    static int n;
    static List<List<Integer>> adj;
    public int numSimilarGroups(String[] strs) {
        n = strs.length;
        int ans = 0;
        vis = new boolean[n];
        adj = new ArrayList<>();
        for(int i = 0; i < n; i++)
            adj.add(new ArrayList<>());
        for(int i = 0; i < n; i++) {
            for(int j = i + 1; j < n; j++) {
                if(isSimilar(strs[i], strs[j])) {
                    adj.get(i).add(j);
                    adj.get(j).add(i);
                }
            }
        }
        for(int i = 0; i < adj.size(); i++) {
            if(!vis[i]) {
                dfs(i);
                ans++;
            }
        }
        return ans;
    }
    private static void dfs(int node) {
        vis[node] = true;
        for(Integer neigh : adj.get(node)) {
            if(!vis[neigh])
                dfs(neigh);
        }
    }

    private static boolean isSimilar(String a, String b) {
        if(a.length() != b.length())    return false;
        int ct = 0; // ct has to be 0 or 2 for both words to be similar
        for(int i = 0; i < a.length(); i++)
            if(a.charAt(i) != b.charAt(i)) {
                ct++;
                if(ct > 2)  return false;
            }
        return ct == 0 || ct == 2 ? true : false;
    }
}