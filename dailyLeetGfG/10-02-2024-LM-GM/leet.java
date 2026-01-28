class Solution {
    private static int n;
    public int countSubstrings(String s) {
        n = s.length();
        int ans = 0;
        for(int i = 0; i < n; i++) {
            int x = getPalin(s,i - 1, i + 1);
            int y = getPalin(s, i - 1, i);
            ans += x + y;
        }
        return ans + n;
    }
    private static int getPalin(String s, int x, int y) {
        int ct = 0;
        if(y < 0 || x < 0)   return ct;
        while(x >= 0 && y < n && s.charAt(x) == s.charAt(y)) {
            ct++;
            x--;
            y++;
        }
        return ct;
    }
}