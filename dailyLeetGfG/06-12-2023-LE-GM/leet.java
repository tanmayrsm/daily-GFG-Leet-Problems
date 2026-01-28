class Solution {
    public int totalMoney(int n) {
        int ans = 0;
        int week = 0, ct = 0, ctr = 1;
        while(ctr <= n) {
            if((ctr-1) % 7 == 0) {
                week++;  ct = 0;
            }
            ans += week + ct;
            ct++;
            ctr++;
        }
        return ans;
    }
}