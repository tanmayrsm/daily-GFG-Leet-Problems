
class Solution {
    public static int stockBuyAndSell(int n, int[] prices) {
        // code here
        int free = 0;
        int hold = -prices[0];
        for(int i:prices){
            int tmp = hold;
            hold = Math.max(hold, free-i);
            free = Math.max(free, tmp+i);
        }
        return free;
    }
}
        
