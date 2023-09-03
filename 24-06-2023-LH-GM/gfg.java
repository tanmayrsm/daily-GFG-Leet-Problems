
class Solution
{
    public int klengthpref(String[] arr, int n, int k, String str)
    {
        // code here
        int ans = 0;
        if(k > str.length())
            return ans;
        String subStr = str.substring(0, k);
        for(String g : arr) {
            if(g.length() >= k && subStr.equals(g.substring(0, k)))
                ans++;
        }
        return ans;
    }
}