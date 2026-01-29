
class Solution{
    private static char[] c;
    static String longestPalin(String S){
        // code here
        c = S.toCharArray();
        if(c.length == 1)   return S;
        int l = S.length();
        int r = 0;
        int ct = -1;
        for(int i = 0; i < S.length(); i++) {
            int[] odder = oddLen(c, i);
            int[] evener = evenLen(c, i);
            if(odder[1] - odder[0] > ct) {
                ct = odder[1] - odder[0];
                l = odder[0];
                r = odder[1];
            }
            if(evener[1] - evener[0] > ct) {
                ct = evener[1] - evener[0];
                l = evener[0];
                r = evener[1];
            }
        }
        return S.substring(l, r + 1);
    }
    private static int[] oddLen(char[] S, int x) {
        int l = x - 1;
        int r = x + 1;
        int ct = 1;
        while(l >= 0 && r < S.length && S[l] == S[r]) {
            l--;
            r++;
            ct += 2;
        }
        return new int[]{l + 1, r - 1};
    }
    
    private static int[] evenLen(char[] S, int x) {
        int l = x;
        int r = x + 1;
        int ct = 0;
        while(l >= 0 && r < S.length && S[l] == S[r]) {
            l--;
            r++;
            ct += 2;
        }
        return ct == 0 ? new int[] {x,x} : new int[]{l + 1, r - 1};
    }
}