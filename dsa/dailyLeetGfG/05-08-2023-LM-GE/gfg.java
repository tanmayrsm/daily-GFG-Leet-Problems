
class Solution
{
    public long findMinDiff (ArrayList<Integer> a, int n, int m)
    {
        // your code here
        Collections.sort(a);
        long ans = Long.MAX_VALUE;
        for(int i = m-1; i < n; i++)
            ans = Math.min(ans, a.get(i) - a.get(i - (m-1)));
        return ans;
    }
}