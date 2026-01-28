
class Solution
{
    public int is_bleak(int n) {
        // Code here
        
        // largest no can be 10^ 9
        // as the hint says, a no 'n' can have atmost logn to base 2 no of setBits
        // hence 10^9 can have atmost 29.89 ~ 30 set bits
        // x + countBits(x) = n
        // where range of each setBits = [1 , 30],
        // so range of x -
        // x = n - 1, x = n - 30 = [m - 1, n - 30]
        // hence ->
        // [n - 1, n - 30] + [1,30] = n
        
        // eg -> n = 35
        // from i = 5 to 34, check if i + countBits(i) == n
        
        for(int i = n - 30; i <= n - 1; i++) {
            if(i + getSetBits(i) == n)  return 0;
        }
        return 1;
    }
    
    
    
    private static int getSetBits(int n) {
        int ct = 0;
        while(n > 0) {
            n = n & (n - 1);
            ct++;
        }
        return ct;
    }
}