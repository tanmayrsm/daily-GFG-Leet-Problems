// User function Template for Java
class Solution {
    int[] dp;
    public int countWays(String digits) {
        // code here
        int m = digits.length();
        dp = new int[m];
        Arrays.fill(dp, -1);
        return solve(digits, m, 0);
    }
    private int solve(String digits, int m, int curr) {
        if (curr == m)  return 1;
        if (dp[curr] != -1) return dp[curr];
        
        char currChar = digits.charAt(curr);
        if (currChar == '0')    return 0;
        int take = 0, nottake = 0;
        take = solve(digits, m, curr + 1);
        if (curr + 1 < m) {
            char next = digits.charAt(curr + 1);
            // if (next == '0')    return 0;
            String no = currChar + "" + next;
            int n = Integer.parseInt(no);
            if (n >= 10 && n <= 26)
                nottake = solve(digits, m, curr + 2);
        }
        return dp[curr] = take + nottake;
    }
}