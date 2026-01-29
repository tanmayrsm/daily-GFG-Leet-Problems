class Solution {
    private static int[] dp;
    public int numDecodings(String s) {
        int n = s.length();
        dp = new int[n];
        Arrays.fill(dp, -1);
        int ans = solve(0, s, n);
        return ans == Integer.MIN_VALUE ? 0 : ans;
    }

    private static int solve(int curr, String s, int n) {
        if(curr >= n)   return 1;
        if(s.charAt(curr) == '0') return 0;
        if(dp[curr] != -1)  return dp[curr];

        int take1 = 0, take2 = 0;
        if((curr + 1 < n && s.charAt(curr + 1) != '0') || (curr + 1 >= n)) 
            take1 = solve(curr + 1, s, n);
        if(curr + 1 < n && Integer.parseInt(s.substring(curr, curr + 2)) <= 26)
            take2 = solve(curr + 2, s, n);

        return dp[curr] = take1 + take2;
    }
}