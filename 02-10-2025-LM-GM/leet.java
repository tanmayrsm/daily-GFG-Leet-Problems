class Solution {
    public int maxBottlesDrunk(int numBottles, int numExchange) {
        int full = numBottles, empty = 0, ans = 0;
        while (full + empty >= numExchange) {
            if (empty >= numExchange) {
                empty -= numExchange;
                full++;
                numExchange++;
            } else if (empty + full >= numExchange) {
                empty += full;
                ans += full;
                full = 0;
            }
        }
        ans += full;
        return ans;
    }
}