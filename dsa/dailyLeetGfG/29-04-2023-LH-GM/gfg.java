class Solution
{
    public long findNumber(long n)
    {
        if(n <= 5) {
            if(n == 1)  return 1;
            if(n == 2)  return 3;
            if(n == 3)  return 5;
            if(n == 4)  return 7;
            if(n == 5)  return 9;
        }
        if(n % 5 == 0)
            return findNumber(n/5 - 1) * 10 + 9;
        return findNumber(n/5) * 10 + findNumber(n % 5); 
    }
}