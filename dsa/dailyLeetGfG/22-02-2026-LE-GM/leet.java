class Solution {
    public int binaryGap(int n) {
        int prev = -1, curr = -1, i = 0, ans = 0;
        while(n > 0) {
            int currBit = n & 1;
            if(currBit == 1) {
                if(prev == -1)  prev = i;
                else {
                    ans = Math.max(ans, i - prev);
                    prev = i;
                }
            }
            i++;
            n >>= 1;
        }
        return ans;
    }
}