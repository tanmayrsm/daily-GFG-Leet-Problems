class Solution {
    public boolean hasAlternatingBits(int n) {
        int last = n & 1;
        n >>= 1;
        while(n > 0) {
            int curr = n & 1;
            if(curr == last)    return false;
            last = curr;
            n >>= 1;
        }
        return true;
    }
}