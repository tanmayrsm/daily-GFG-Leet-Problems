class Solution {
    public int lengthOfLastWord(String s) {
        // String[] sp = s.split(" ");
        // return sp[sp.length - 1].length();
        int n = s.length() - 1, ans = 0;
        while(n >= 0 && s.charAt(n) == ' ')   n--;
        while(n >= 0 && s.charAt(n) != ' ') {
            n--;
            ans++;
        }
        return ans;
    }
}