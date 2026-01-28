
class Solution {
    private static Set<String> ans;
    public ArrayList<String> findPermutation(String S) {
        // Code here
        ans = new TreeSet<>();
        solve(S.toCharArray(), 0);
        return new ArrayList<>(ans);
    }
    private static void solve(char[] s, int ptr) {
        if(ptr == s.length) {
            ans.add(new String(s));
            return;
        }
        for(int i = ptr; i < s.length; i++) {
            swap(s, i, ptr);
            solve(s, ptr + 1);
            swap(s, i, ptr);
        }
    }
    private static void swap(char[] c, int i, int j) {
        char temp = c[i];
        c[i] = c[j];
        c[j] = temp;
    }
}