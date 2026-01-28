class Solution {
    public int maxProfit(int[] prices, int fee) {
        // dp approach using 2 variables
        int free = 0;
        int hold = -prices[0];
        for(int i:prices){
            int tmp = hold;
            hold = Math.max(hold, free-i);
            free = Math.max(free, tmp+i-fee);
        }
        return free;
    }
}