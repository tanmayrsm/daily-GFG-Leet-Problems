class Solution {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g); Arrays.sort(s);
        int l = 0, r = 0, ans = 0, n = g.length, m = s.length;
        while(l < n && r < m) {
            while(r < m && s[r] < g[l])  r++;
            if(r < m) {
                ans++;
                l++;
                r++;
            }
        }    
        return ans;
    }
}