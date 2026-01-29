class Solution {
    public boolean canMakeSubsequence(String str1, String str2) {
        int n = str1.length(), m = str2.length();
        int c1 = 0, c2 = 0;
        while (c1 < n && c2 < m) {
            char s1 = str1.charAt(c1), s2 = str2.charAt(c2);
            if(s1 == s2 || s1 + 1 == s2 || s1 == 'z' && s2 == 'a') {
                c2++;
            }
            c1++;
        }
        return c2 == m;
    }
}