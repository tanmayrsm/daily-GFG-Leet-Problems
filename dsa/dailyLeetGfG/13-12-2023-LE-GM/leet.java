
class Solution {
    private static long mod = 1000000007;
    private static long[][] dp;
    long countStrings(int n) {
        // code here
        // fibo type sum
        long mod = 1000000007;
        long a = 2;  
        long b = 3,c;  

        if (n == 1) {
            return 2;
        } else if (n == 2) {
            return 3;
        }

        for (int i = 3; i <= n; i++) {
            c = b;
            b = (a + b) % mod;
            a = c;
        }

        return b;
    }
}