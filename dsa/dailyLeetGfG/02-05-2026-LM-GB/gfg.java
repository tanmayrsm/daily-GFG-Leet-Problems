class Solution {
    public int findPosition(int n) {
        // code here
        int ct = 0, pos = 1, ans = -1;
        while(n > 0) {
            int dig = n & 1;
            if(dig == 1) {
                ans = pos;
                ct++;
            }
            pos++;
            n >>= 1;
        }
        return (ct == 1) ? ans : -1;
    }
}