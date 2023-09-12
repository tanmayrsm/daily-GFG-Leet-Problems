
class Solution
{
    // Complete the function
    // n: Input n
    // Return True if the given number is a lucky number else return False
    public static boolean isLucky(int n)
    {
        // Your code here
        
        // referred vid soln - https://www.youtube.com/watch?v=-Xzj2G-BkFs
        // only concern about poistion of 'n' after each counter operations
        return isL(n, 2);
        
    }
    
    private static boolean isL(int n, int ct) {
        if(n % ct == 0) return false;
        if(ct > n)  return true;
        return isL(n - n / ct, ct + 1);
    }
}