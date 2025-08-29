class Solution {
    public long flowerGame(int n, int m) {
        int xE = n / 2, xO = (n / 2) + ((n % 2 == 1) ? 1 : 0);
        int yE = m / 2, yO = (m / 2) + ((m % 2 == 1) ? 1 : 0);
        long res1 = (long)xE*(long)yO , res2 = (long)xO*(long)yE;
        return res1+res2;
    }
}