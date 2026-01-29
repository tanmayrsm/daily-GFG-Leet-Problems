class Solution {
    StringBuilder ans = new StringBuilder();
    List<List<Character>> arr;
    boolean[] vis;

    public void dfs(int s) {
        vis[s] = true;
        for (char u: arr.get(s)) {
            if (!vis[u - 'a']) {
                dfs(u - 'a');
            }
        }
        ans.insert(0, (char)(s + 'a'));
    }

    public String findOrder(String [] dict, int n, int k) {

        arr = new ArrayList<>();
        vis = new boolean[k];

        for (int i = 0; i < k; i++) {
            arr.add(new ArrayList<Character>());
        }

        for (int i = 0; i < n - 1; i++) {
            String a = dict[i];
            String b = dict[i + 1];
            for (int j = 0; j < Math.min(a.length(), b.length()); j++) {
                if (a.charAt(j) != b.charAt(j)) {
                    arr.get(a.charAt(j) - 'a').add(b.charAt(j));
                    break;
                }
            }
        }

        for (int i = 0; i < k; i++) {
            if (!vis[i]) {
                dfs(i);
            }
        }
        return ans.toString();
    }
}