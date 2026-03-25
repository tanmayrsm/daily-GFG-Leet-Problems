/*
class Node {
    int data;
    Node left;
    Node right;

    Node(int data) {
        this.data = data;
        left = null;
        right = null;
    }
}
*/

class Solution {
    private int ans;
    public int countAllPaths(Node root, int k) {
        // code here
        Map<Integer, Integer> st = new HashMap<>();
        ans = 0;
        st.put(0, 1);
        dfs(root, st, Integer.MIN_VALUE, k);
        return ans;
    }
    private void dfs(Node root, Map<Integer, Integer> st, int pref, int k) {
        if(root == null)    return;
        if(pref == Integer.MIN_VALUE)  pref = root.data;
        else pref += root.data;

        if (st.containsKey(pref - k))    ans += st.get(pref - k);

        st.put(pref, st.getOrDefault(pref, 0) + 1);

        dfs(root.left, st, pref, k);
        dfs(root.right, st, pref, k);

        if(st.get(pref) == 1)   st.remove(pref);
        else st.put(pref, st.get(pref) - 1);
    }
}