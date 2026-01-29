import java.util.HashMap;
import java.util.PriorityQueue;

class Solution {
    public int[] processQueries(int c, int[][] connections, int[][] queries) {
        int[] parent = new int[c];
        List<Integer> ans = new ArrayList<>();
        boolean[] vis = new boolean[c];
        Map<Integer, TreeSet<Integer>> mp = new HashMap<>();
        Arrays.fill(parent, -1);

        for (int[] con : connections) {
            int minX = Math.min(con[0], con[1]);
            int maxX = Math.max(con[0], con[1]);
            parent[maxX - 1] = minX - 1;
        }
        for (int i = 0; i < c; i++) {
            if (parent[i] == -1) {
                // x : 2,4,5...
                mp.put(i, new TreeSet<>());
                mp.get(i).add(i);
            }
        }
        for (int i = 0; i < c; i++) {
            if (!vis[i] && parent[i] != -1) {
                dfs(i, parent, vis, mp);
            }
        }
        System.out.println("mp ::" + mp);

        for (int[] query : queries) {
            int q = query[0], node = query[1];
            int p = parent[node];
            if(p == -1) p = node;
            if (q == 1) {
                if (mp.get(p).isEmpty())
                    ans.add(-1);
                else ans.add(mp.get(p).first());
            } else {    // make it offline
                mp.get(p).remove(node);
            }
        }
        return ans.stream().toArray();
    }
    private int dfs(int c, int[] parent, boolean[] vis, Map<Integer, TreeSet<Integer>> mp) {
        if (parent[c] == -1)    return c;
        if (vis[c]) return parent[c];
        int originatorParent = dfs(parent[c], parent, vis, mp);
        vis[c] = true;
        parent[c] = originatorParent;
        mp.get(originatorParent).add(c);
        return originatorParent;
    }
}