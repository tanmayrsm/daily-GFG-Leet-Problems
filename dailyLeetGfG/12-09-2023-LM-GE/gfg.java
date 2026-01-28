
class Solution {
    static int isPerfectNumber(long N) {
        // code here
        if(N == 1)  return 0;   // exception - as only divisor is 1 itself, and perfect nos means sum of all divisors except no itself
        
        long ct = 1;
        for(long i = 2; i <= Math.sqrt(N); i++) {
            if(N % i == 0) {
                ct += i;
                if(N  != i * i)
                    ct += N / i;
            }
        }
        if(ct == N) return 1;
        return 0;
    }
};