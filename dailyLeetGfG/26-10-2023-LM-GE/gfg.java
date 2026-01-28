
class Solution
{
    public int minOperation(int n)
    {
        //code here.
        int ct = 0;
        while(n > 0) {
            if(n %2 == 0)
                n /= 2;
            else n--;
            ct++;
        }
        return ct;
    }
}