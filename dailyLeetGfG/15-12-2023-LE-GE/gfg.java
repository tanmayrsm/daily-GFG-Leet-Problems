
class Solution
{
    public int nthPoint(int n)
    {
        // Code here
        if(n <= 2)  return n;
        long start = 1, prev = 2, ans = 0;
        for(int i = 3; i <= n; i++) {
            ans = (start + prev) % 1000000007;
            start = prev;
            prev = ans;
        }
        return (int)ans;
    }
}