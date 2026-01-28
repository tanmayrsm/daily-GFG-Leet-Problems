class Solution {
    private static int[][] dp;
    public int maxValue(int[][] events, int k) {
        Arrays.sort(events, (a, b) -> Integer.compare(a[0], b[0]));
        int n = events.length;
        dp = new int[n + 1][k + 1];
        for(int[] x :  dp)
            Arrays.fill(x, -1);

        return solve(events, 0, k, 0);
    }
    private static int solve(int[][] events, int currIndex, int picked, int lastEndValue) {
        if(picked == 0 || currIndex == events.length) {
            return 0;
        }

        if(events[currIndex][0] <= lastEndValue) {
            return solve(events, currIndex + 1, picked, lastEndValue);
        } 

        if(dp[currIndex][picked] != -1)
            return dp[currIndex][picked];

        int choice = Math.max(
            events[currIndex][2] + solve(events, currIndex + 1, picked - 1, events[currIndex][1]),
            solve(events, currIndex + 1,picked, lastEndValue)
        );

        return dp[currIndex][picked] = choice;
    }
}