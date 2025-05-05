class Solution {
    public int numTilings(int n) {
        if (n == 1) return 1;
        if (n == 2) return 2;
        if (n == 3) return 5;
        long last2last = 2, last = 5, last3last = 1;
        for (int i = 3; i < n; i++) {
            long newer = (2 * last + last3last);
            last3last = last2last % 1000000007;
            last2last = last % 1000000007;
            last = newer % 1000000007;
        }
        return (int)last;
    }
}

// based on observation
// n =  1 2 3 4  5  6  7.  8
// o.p= 1 2 5 11 24 53 117 258