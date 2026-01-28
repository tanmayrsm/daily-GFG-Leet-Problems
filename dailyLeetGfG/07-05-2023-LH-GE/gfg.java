
class Solution {
    public static String stringMirror(String str) {
        // code here
        char[] s = str.toCharArray();
        int n = str.length();
        StringBuilder res = new StringBuilder();
        res.append(s[0]);
        for(int i = 1; i < n; i++) {
            if(s[i] < s[i-1] || (i > 1 && s[i] == s[i-1])) {
                res.append(s[i]);
            } else  break;
        }
        StringBuilder g = new StringBuilder(res.reverse());
        res.reverse().append(g);
        return res.toString();
    }
}