class Solution {
    public ArrayList<Integer> findOrder(int n, int[][] pre) {
        // code here
        ArrayList<Integer> ans = new ArrayList<>();
        int[] inorder = new int[n];
        boolean[] vis = new boolean[n];
        Arrays.fill(inorder, -1);
        List<List<Integer>> adj = new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();

        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int[] p : pre) {
            adj.get(p[1]).add(p[0]);

            if (inorder[p[0]] == -1)    inorder[p[0]]++;
            inorder[p[0]]++;
        }
        for(int i = 0; i < n; i++)
            if (inorder[i] == -1)
                q.offer(i);

        while(!q.isEmpty()) {
            int curr = q.poll();
            ans.add(curr);
            for(Integer neigh : adj.get(curr)) {
                inorder[neigh]--;
                if (!vis[neigh] && inorder[neigh] == 0) {
                    vis[neigh] = true;
                    q.offer(neigh);
                }
            }
        }

        return ans;
    }
}