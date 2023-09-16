
class Solution
{
    static int m = 1000000007;
    //Function to count the number of ways in which frog can reach the top.
    static long countWays(int n)
    {
        // add your code here
        // in fibo u take sum of last two elemn, here u take sum of last 3 elemns
        if(n == 1)  return 1;
        if(n == 2)  return 2;
        if(n == 3)  return 4;

        return count(1,2,4,4,n);
    }
    
    private static long count(long p1, long p2, long p3, int ct, int n) {
        long g = p1 + p2 + p3;
        long next = g % m;
        if(ct == n) {
            return next;
        }
        return count(p2, p3, next, ct + 1, n);
    }
    
}
