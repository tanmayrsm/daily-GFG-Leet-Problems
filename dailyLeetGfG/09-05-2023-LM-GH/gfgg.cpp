
class Solution {
  public:
    long long fib(long long n) {
        long long a, b, p, q;
        long long Mod = 1e9 + 7;
        a = q = 1;
        b = p = 0;
        while (n > 0) {
            if (n % 2 == 0) {
                long long qq = (q*q) %Mod;
                q = (2*((p*q)%Mod) + qq) %Mod;
                p = ((p*p) %Mod + qq) %Mod;
                n /= 2;
            } else {
                long long aq = (a*q) % Mod;
                a = ((b*q) %Mod + aq + (a*p)  %Mod)  %Mod;
                b = ((b*p) %Mod + aq) %Mod;
                n -= 1;
            }
            b = b %Mod;
            q = q %Mod;
            a = a %Mod;
            b = b %Mod;
        }
        return b %Mod;
    }
    int countStrings(long long int N) {
        // Code here
        return fib(N + 2);
    }
};