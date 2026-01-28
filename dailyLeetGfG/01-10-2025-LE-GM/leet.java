class Solution {
    public int numWaterBottles(int numBottles, int numExchange) {
        int ans = (numBottles / numExchange) * numExchange;
        int rest = numBottles % numExchange + numBottles / numExchange;
        while (rest > 0) {
            if (rest - numExchange >= 0) {
                rest -= numExchange;
                ans += numExchange;
            } else {
                ans += rest;
                rest = 0;
                break;
            }
            rest++;
        }
        return ans;
    }
}