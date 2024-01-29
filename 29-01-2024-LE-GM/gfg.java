class Solution
{
    public int rep(int index, String s, Integer[][] dp, int sum)
    {
        if(index >= s.length())
            return 1;
        
        if(dp[index][sum] != null)
            return dp[index][sum];
        
        int curSum = 0;
        dp[index][sum] = 0;
        
        for(int i = index; i < s.length(); ++i) {
            curSum += (s.charAt(i)-'0');
            if(curSum >= sum)
                dp[index][sum] += rep(i+1, s, dp, curSum);
        }
        
        return dp[index][sum];
    }
    
    public int TotalCount(String s)
    {
        Integer[][] dp = new Integer[s.length()][907];
        return rep(0, s, dp, 0);
    }
}