
class Solution{
    
    long numberOfPaths(int M, int N) {  // referred soln
        // Code Here
        int t = M + N - 2;
        int r = M - 1;
        long res = 1;
        final int MOD = 1000000007;
    
        for (int i = 1; i <= r; i++) {
            res = (res * (t - r + i) % MOD) * modInverse(i, MOD) % MOD;
        }
    
        return res;
    }
    
    long modInverse(int a, int m) {
    long m0 = m;
    long x0 = 0;
    long x1 = 1;

    while (a > 1) {
            long q = a / m;
            long t = m;
    
            m = a % m;
            a = (int)t;
    
            t = x0;
            x0 = x1 - q * x0;
            x1 = t;
        }

        if (x1 < 0) {
            x1 += m0;
        }

        return x1;
    }
    
}