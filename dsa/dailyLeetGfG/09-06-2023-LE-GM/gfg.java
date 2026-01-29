
class Solution {
    static Set<String> ans;
    public List<String> find_permutation(String S) {
        // Code here
        ans = new LinkedHashSet<>();    // use linked set, as we dont need duplicate ans, and maintain order of each
                                        // element addition in set
        List<String> r = new ArrayList<>();
        char[] inp = S.toCharArray();
        Arrays.sort(inp);               // if input string is unsorted, algo below will compute results as per curr
                                        // order of char in String, hence sorting them before calling algo
        solve(new String(inp), 0);
        r.addAll(ans);
        return r;
    }
    private static void solve(String s, int curr) {
        if(curr >= s.length()) {
            ans.add(s);
        }
        for(int i = curr; i < s.length(); i++) {
            s = swap(s, curr, i);
            solve(s, curr + 1);
        }
    }
    private static String swap(String s, int i, int j) {
        char[] g = s.toCharArray();
        char temp = g[i];
        g[i] = g[j];
        g[j] = temp;
        return new String(g);
    }
}