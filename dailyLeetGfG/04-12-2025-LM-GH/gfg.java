class Solution {
    int[][] dp;

    public int minCost(int keys[], int freq[]) {
        int n = keys.length;
        dp = new int[n][n];
        for(int[] x: dp)
            Arrays.fill(x, -1);
        return solve(freq, 0, n - 1);
    }

    private int solve(int[] freq, int l, int r) {
        if (l > r)  return 0;
        if (l == r) return freq[l];

        if(dp[l][r] != -1)   return dp[l][r];

        // Sum of frequencies in range [l, r]
        int sum = 0;
        for(int i = l; i <= r; i++) {
            sum += freq[i];
        }

        int min = Integer.MAX_VALUE;

        // Try each key as root
        for (int i = l; i <= r; i++) {
            int cost = sum + solve(freq, l, i - 1) + solve(freq, i + 1, r);
            min = Math.min(min, cost);
        }

        return dp[l][r] = min;
    }
}