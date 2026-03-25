class Solution {
    public boolean canFinish(int n, int[][] prerequisites) {
        // code here
        List<List<Integer>> adj = new ArrayList<>();
        int[] inorder = new int[n];
        Queue<Integer> q = new LinkedList<>();
        Arrays.fill(inorder, -1);
        for (int i = 0; i < n; i++)
            adj.add(new ArrayList<>());
        for(int[] e : prerequisites) {
            int from = e[0], to = e[1];
            adj.get(to).add(from);
            if(inorder[from] == -1) inorder[from] = 0;
            inorder[from]++;
        }
        for(int i = 0; i < n; i++)  if (inorder[i] == -1)   q.offer(i);
        while (!q.isEmpty()) {
            int curr = q.poll();
            for(int neigh : adj.get(curr)) {
                inorder[neigh]--;
                if(inorder[neigh] == 0) {
                    inorder[neigh] = -1;
                    q.offer(neigh);
                }
            }
        }
        for(int i = 0; i < n; i++)  if (inorder[i] > 0) return false;
        return true;
    }
}


