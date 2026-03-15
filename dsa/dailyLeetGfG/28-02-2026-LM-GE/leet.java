class Solution {
    public int concatenatedBinary(int n) {
        long res = 0;
        int bits = 0;
        int mod = 1000000000 + 7;
        for(int i = 1; i <= n; i++) {   // O(n)
            if((i & (i - 1)) == 0)  bits++;
            res = ((res << bits) + i) % mod;
        }
        return (int)res;
    }
}

// 01 10 11 100 101

