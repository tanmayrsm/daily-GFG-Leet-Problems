
// User function Template for Java

class Solution {
    String removeDups(String str) {
        // code here
        int[] ct = new int[26];
        int n = str.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char c = str.charAt(i);
            if (ct[c - 'a'] == 0) {
                sb.append(c + "");
            }
            ct[c - 'a']++;
        }
        return sb.toString();
    }
}