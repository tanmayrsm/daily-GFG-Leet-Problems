
class Solution {
    public int maximumProfit(int prices[]) {
        // Code here
        int n = prices.length, ans = 0, maxo = prices[n - 1];
        for(int i = n - 2; i >= 0; i--) {
            ans = Math.max(ans, maxo - prices[i]);
            maxo = Math.max(maxo, prices[i]);
        }
        return ans;
    }
}