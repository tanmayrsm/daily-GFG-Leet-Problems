import java.util.Arrays;

class Solution {
    public int maximumCandies(int[] candies, long k) {
        int n = candies.length;
        int maxo = Arrays.stream(candies).max().getAsInt(), mino = 1, maxChild = 0;
        while (mino <= maxo) {
            int mid = (maxo + mino) / 2;
            // System.out.println("trying ::" + mid);
            if (isPilePossible(mid, candies, k)) {
                // System.out.println("done");
                maxChild = mid;
                mino = mid + 1;
            } else {
                maxo = mid - 1;
            }
        }
        return maxChild;
    }
    private boolean isPilePossible(int pileSize, int[] candies, long k) {
        int n = candies.length;
        long servedChildren = 0;
        for (int i = 0; i < n; i++) {
            if (candies[i] >= pileSize) {
                servedChildren += candies[i] / pileSize;
            }
            if (servedChildren >= k)    
                return true;
        }
        return false;
    }
}