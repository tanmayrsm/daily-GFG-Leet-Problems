class Solution {
    public long maxProfit(int[] prices, int[] strategy, int k) {
        long ans = 0, curr = 0;
        int n = prices.length, halfK = k / 2;
        for (int i = 0; i < prices.length; i++)
            ans += strategy[i] * prices[i];
        curr = 0;
        for (int i = halfK; i < k; i++) {
            curr += prices[i];
        }
        for (int i = k; i < n; i++)
            curr += strategy[i] * prices[i];
        // slider window
        int l = 0, mid = halfK, r = k;
        while (r < n) {
            ans = Math.max(ans, curr);
            if (strategy[r] == 0)
                curr += prices[r];
            else if (strategy[r] == -1)
                curr += 2 * prices[r];

            curr -= prices[mid];
            curr += strategy[l] * prices[l];
            r++;
            mid++;
            l++;
        }

        return Math.max(ans, curr);
    }
}