class Solution {
    public int longestUniqueSubstr(String s) {
        // code here
        int n = s.length(), l = 0, ans = 0;
        HashSet<Character> st = new HashSet<>();
        for (int i = 0; i < n; i++) {
            char curr = s.charAt(i);
            while(l < i && st.contains(curr)) {
                st.remove(s.charAt(l++));
            }
            st.add(curr);
            ans = Math.max(ans, i - l + 1);
        }
        return ans;
    }
}