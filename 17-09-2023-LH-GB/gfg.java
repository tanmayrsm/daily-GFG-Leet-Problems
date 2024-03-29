
class Solution
{
    //Function to return list containing first n fibonacci numbers.
    public static long[] printFibb(int n) 
    {
        //Your code 
        long[] ans = new long[n];
        ans[0] = 1;
        if(n == 1)  return ans;
        ans[1] = 1;
        for(int i = 2; i < n; i++)
            ans[i] = ans[i - 1] + ans[i - 2];
        return ans;
    }
}