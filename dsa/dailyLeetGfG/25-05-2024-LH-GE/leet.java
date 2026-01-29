class Solution {
    private static Set<String> st;
    private static List<String> ans;
    public List<String> wordBreak(String s, List<String> wordDict) {
        st = new HashSet<>(wordDict);
        ans = new ArrayList<>();
        solve(s, new StringBuilder(), 0, s.length(), new StringBuilder());
        return ans;
    }
    private static void solve(String s, StringBuilder sb, int curr, int n, StringBuilder current) {
        if(curr == n) {
            if(st.contains(sb.toString())) {
                StringBuilder old = new StringBuilder(current);
                current.append(" ");
                current.append(sb.toString());
                ans.add(current.toString().trim());
                current = new StringBuilder(old);
            }
            return;
        }
        if(st.contains(sb.toString())) {
            StringBuilder old = new StringBuilder(current);
            current.append(" ");
            current.append(sb.toString());
            solve(s, new StringBuilder(s.charAt(curr) + ""), curr + 1, n, current);
            current = new StringBuilder(old);
        }
        StringBuilder old = new StringBuilder(sb);
        sb.append(s.charAt(curr) + "");
        solve(s, sb, curr + 1, n, current);
        sb = new StringBuilder(old);
    }
}