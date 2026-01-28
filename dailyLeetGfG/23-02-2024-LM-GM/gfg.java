
class Solution {    // reff
    public static int maxProfit(int n, int[] prices) {
       int firstBuy = Integer.MIN_VALUE;
       int firstSell = 0;
       int secondBuy = Integer.MIN_VALUE;
       int secondSell = 0;
       
       for (Integer price: prices) {
           firstBuy = Math.max(firstBuy, -price);
           firstSell = Math.max(firstSell, price+firstBuy);
           secondBuy = Math.max(secondBuy, firstSell-price);
           secondSell = Math.max(secondSell, secondBuy+price);
       }
       
       return secondSell;
   }
}
       
