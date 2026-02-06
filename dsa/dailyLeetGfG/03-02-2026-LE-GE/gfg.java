class Solution {
    public int maxProfit(int[] prices) {
        // Code here
        int n = prices.length, ans = 0;
        int maxo = prices[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            if(prices[i] > maxo)    maxo = prices[i];
            else ans = Math.max(ans, maxo - prices[i]);
        }
        return ans;
    }
}