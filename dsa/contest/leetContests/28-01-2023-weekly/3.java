class Solution {
    public long flowerGame(int n, int m) {
        // so if x and y are even both, i.e x+y %2 == 0
        // no change for alice to win
        // but if one side is odd, (either x or y), x+y % 2 ==1, alice will win
        // basic -> 1,2,3, if alice starts, 1-> alice, 2-> bob, last one alice, hence she will win
        
        // thus for each even no between [1, n] * all odd nos between [1, m]
        // for each odd no between [1, n] * all even nos between [1, m]
        
        long evenX = n / 2, oddX = (n + 1) / 2;
        long evenY = m / 2, oddY = (m + 1) / 2;
        
        return evenX * oddY + oddX * evenY;
        
    }
}