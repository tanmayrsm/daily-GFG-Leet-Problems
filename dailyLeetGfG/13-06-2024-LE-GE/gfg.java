
class Solution
{
  public int padovanSequence(int n)
    {
        //code here.
        int mod = 1000000007;
        if (n == 0 || n == 1 || n == 2)
            return 1;
        int a = 1, b = 1, c = 1;
        for(int i = 3; i <= n; i++)
        {
            int sum = (a + b) % mod;
            a = b;
            b = c;
            c = sum;
        }
        return c;
    }
    
}