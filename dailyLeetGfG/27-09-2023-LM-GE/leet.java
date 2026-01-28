class Solution {
    // referred - https://www.youtube.com/watch?v=vmgVNV0u3pE
    public String decodeAtIndex(String s, int k) {
        int n = s.length();
        long ct = 0;
        long[] lenOfDecoded = new long[n];
        // s =            l e e t 2 c o  d  e  3
        // lenOfDecoded = 1 2 3 4 8 9 10 11 12 36
        for(int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if(!Character.isDigit(c)) {
                ct++;
            } else {
                Integer no = c - '0';
                ct *= no;
            }
            lenOfDecoded[i] = ct;
        }

        int ptr = n - 1;
        long kk = Long.valueOf(k);  // just converting, as lenOfDecoded has long values
        while(lenOfDecoded[ptr] > kk) {
            ptr--;
            if(lenOfDecoded[ptr] < kk) {
                kk = (kk - 1) % lenOfDecoded[ptr] + 1L; // to avoid 0 based indexing, have used (kk - 1) % mod + 1
            }
        }

        while(Character.isDigit(s.charAt(ptr)))    ptr--;
        return s.substring(ptr, ptr + 1);

    }
}