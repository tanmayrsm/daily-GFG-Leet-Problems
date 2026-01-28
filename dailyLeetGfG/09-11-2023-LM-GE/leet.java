class Solution {
    public int countHomogenous(String s) {
        int n = s.length();
        long ans = 0, mod = 1000000007;
        for(int i = 0; i < n; i++)  {
            long ct = 1;
            while(i + 1 < n && s.charAt(i) == s.charAt(i +1)) {
                i++;
                ct++;
            }
            ans += ct*(ct + 1) / 2;
            ans %= mod;
        }
        return (int)ans;
    }   
}