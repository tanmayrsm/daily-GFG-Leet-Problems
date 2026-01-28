class Solution
{
    //Function to count number of ways to reach the nth stair.
    int countWays(int n)
    {
        // your code here
        int prev1 = 1;
        if(n == 1) return 1;
        int prev2 = 2;
        for(int i = 3; i <= n; i++) {
            int next = prev1 + prev2;
            prev1 = prev2;
            prev2 = next;
        }
        return prev2;
    }
}