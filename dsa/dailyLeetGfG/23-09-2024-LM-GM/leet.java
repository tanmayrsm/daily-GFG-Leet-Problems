
class Solution {
    private Set<String> dict;
    private int[][] dp;
    public int minExtraChar(String s, String[] dictionary) {
        int n = s.length();
        dict = new HashSet<>();
        dp = new int[n][n];
        for(int[] d : dp)
            Arrays.fill(d, -1);
        for(String d : dictionary)
            dict.add(d);
        return solve(new StringBuilder(), s, 0, n);
    }
    private int solve(StringBuilder sb, String s, int curr, int n) {
        if (curr == n)
            return sb.length();
        int m = sb.length();
        if(dp[curr][m] != -1)   return dp[curr][m];
        sb.append(s.charAt(curr) + "");
        int take = Integer.MAX_VALUE, nottake = Integer.MAX_VALUE, takeAndAppend = Integer.MAX_VALUE;
        if(dict.contains(sb.toString())) {
            take = solve(new StringBuilder(), s, curr + 1, n);
        }
        int r = solve(new StringBuilder(), s, curr + 1, n);
        if (r != Integer.MAX_VALUE)
            nottake = sb.length() + r;
        takeAndAppend = solve(sb, s, curr + 1, n);
        return dp[curr][m] = Math.min(take, Math.min(nottake, takeAndAppend));
    }
}