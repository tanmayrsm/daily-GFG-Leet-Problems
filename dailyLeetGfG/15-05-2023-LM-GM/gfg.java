
class Solution {
    public static long countBits(long n) {
        // code here
        if(n == 0)
            return 0;
        long x = largePowerofTwo(n);
        long y = x * (1 << (x - 1));
        long z = n - (1 << x);
        return y + z + 1 + countBits(z);
    }
    
    private static long largePowerofTwo(long n) {
        long x = 0;
        while((1 << x) <= n)
            x++;
        return x - 1;
    }
}
        
