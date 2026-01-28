
//User function Template for Java

class Solution
{
    public int TotalWays(int N)
    {
        // Code here
        // for N = 
        // 1,2,3,  4, 5,   6
        // 4,9,25,64,169,441...is Ans
        // 2,3,5, 8,13,21....is their sq root
        // hence a fibo seq
        long a = 2, b = 3, mod = 1000000007;
        long ans = 0;
        if(N == 1)  return 4;
        if(N == 2)  return 9;

        for(int i = 2; i < N; i++) {
            ans = a + b;
            ans %= mod;
            long temp = b;
            b = a + b;
            b %= mod;
            a = temp;
        }
        ans *= ans;
        ans %= mod;
        return (int)ans;
    }
}