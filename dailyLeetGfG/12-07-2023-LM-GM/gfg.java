
class Solution
{
        
    long power(int N,int R)
    {
        //Your code here
        return pow(N, R);
        
    }
    private static long pow(int n, int r) {
        long ans = 0;
        if(r == 0)
            return 1;
        if(r % 2 == 0) {    // even r
            ans = pow(n, r / 2);
            ans = ans * ans % 1000000007;
        } else {            // odd r
            ans = n * pow(n, r - 1) % 1000000007;
        }
        return ans % 1000000007;
    }
}