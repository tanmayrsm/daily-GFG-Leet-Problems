
class Solution
{
    long countWays(int n,int k) // reff soln
    {
        //code here.
        long mod = (long) Math.pow(10,9)+7;
        long same=0,diff=k;
        for(int i=2;i<=n;i++){
            long prev=same;
            same=diff;
            diff=(same+prev)*(k-1)%mod;
        }
        return (same+diff)%mod;
    }
}