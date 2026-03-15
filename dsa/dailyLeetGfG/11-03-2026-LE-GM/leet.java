class Solution {
    public int bitwiseComplement(int n) {
        int res = 0, digits = 0;
        do {
            int bit = n & 1;
            if(bit == 0) {
                int newNo = 1 << digits;    // do res | (1000..till digits count)
                res = (res | newNo);
            }

            n >>= 1;    // right shift n by 1
            digits++;
        } while(n > 0);
        return res;
    }
}