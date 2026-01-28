class Solution {
    public boolean isTree(int n, int m, ArrayList<ArrayList<Integer>> edges) {
        // Multiple edges and self-loop
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (ArrayList<Integer> vec : edges) {
            graph.get(vec.get(0)).add(vec.get(1));
            graph.get(vec.get(1)).add(vec.get(0));
        }

        for (int i = 0; i < n; i++) {
            Map<Integer, Integer> mp = new HashMap<>();
            for (int child : graph.get(i)) {
                mp.put(child, mp.getOrDefault(child, 0) + 1);
                // Multiple edges and self-loop
                if (mp.get(child) > 1 || child == i) {
                    return false;
                }
            }
        }

        // Loops
        int[] vis = new int[n];
        Stack<int[]> st = new Stack<>();
        st.push(new int[]{0, -1});
        vis[0] = 1;

        while (!st.isEmpty()) {
            int t = st.peek()[0];
            int par = st.peek()[1];
            st.pop();

            for (int child : graph.get(t)) {
                if (vis[child] == 1 && child != par) {
                    return false;
                }
                if (vis[child] == 0) {
                    vis[child] = 1;
                    st.push(new int[]{child, t});
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if (vis[i] == 0) {
                return false;
            }
        }

        return true;
    }
}