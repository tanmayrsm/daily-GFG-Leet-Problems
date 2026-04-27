class Solution {
    public int smallestSubstring(String s) {
        // code here
        int n = s.length();
        int zc = 0, oc = 0, tc = 0, l = 0, r = 0, ans = Integer.MAX_VALUE;
        while(r < n) {
            while(r < n && (zc == 0 || oc == 0 || tc == 0)) {
                int c = s.charAt(r) - '0';
                if(c == 0)  zc++;
                else if (c == 1)    oc++;
                else tc++;
                r++;
            }
            // System.out.println(zc + "::" + oc + "::" + tc);
            while(l < r && (zc > 0 && oc > 0 && tc > 0)) {
                ans = Math.min(ans, r - l);
                int c = s.charAt(l) - '0';
                if(c == 0)  zc--;
                else if (c == 1)    oc--;
                else tc--;
                l++;
            }
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
};
