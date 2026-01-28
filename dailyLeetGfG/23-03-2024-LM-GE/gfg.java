
class Solution {

    int[] Series(int n) {
        // code here
        int[] fibo = new int[n + 1];
        int mod = 1000000007;
        fibo[0] = 0;
        fibo[1] = 1;
        for(int i = 2; i <= n; i++) {
            long x = fibo[i - 1] + fibo[i - 2];
            fibo[i] = (int)(x % mod);        
        }
        return fibo;
    }
}