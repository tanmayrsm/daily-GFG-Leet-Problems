class Solution {
    public int numSub(String s) {
        int n = s.length(), ans = 0;
        double ones = 0, mod = 1000000007;
        for (int i = 0; i <= n; i++) {
            if (i != n && s.charAt(i) == '1') ones++;
            else if (ones > 0) {
                double res = ((ones) * (ones + 1)) / 2;
                res %= mod;
                ans += (int)res; 
                ones = 0;
            }
        }
        return ans;
    }
}