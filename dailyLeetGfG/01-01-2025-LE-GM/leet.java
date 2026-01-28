class Solution {
    private int[] dp;
    public int mincostTickets(int[] days, int[] costs) {
        dp = new int[days.length];
        Arrays.fill(dp, -1);
        return solve(days, costs, 0);
    }
    private int solve(int[] days, int[] costs, int curr) {
        if (curr == days.length)    return 0;
        if (dp[curr] != -1) return dp[curr];
        int c1 = getNextIndex(days, curr, 1), c2 = getNextIndex(days, curr, 7), c3 = getNextIndex(days, curr, 30);
        
        int take1 = costs[0] + solve(days, costs, c1);
        int take7 = costs[1] + solve(days, costs, c2);
        int take30 = costs[2] + solve(days, costs, c3);

        return dp[curr] = Math.min(take1, Math.min(take7, take30));
    }
    private int getNextIndex(int[] days, int curr, int dayMove) {
        int currDay = days[curr] + dayMove, ans = curr+1;
        while(ans < days.length) {
            if (days[ans] >= currDay) return ans;
            ans++; 
        }
        
        return ans;
    }
}