class Solution {
    private static int MAX = Integer.MAX_VALUE;
    public int minDominoRotations(int[] tops, int[] bottoms) {
        int tryTop = getSwaps(tops, bottoms);
        int tryBottom = getSwaps(bottoms, tops);
        
        // now try swapping top first element with bottom first element
        swap(tops, bottoms, 0);

        int trySwapTop = getSwaps(tops, bottoms);
        int trySwapBottom = getSwaps(bottoms, tops);
        int ans = Math.min(tryTop, Math.min(tryBottom, Math.min(trySwapTop!=MAX ? trySwapTop + 1:MAX, trySwapBottom!= MAX ? trySwapBottom + 1 : MAX)));
        if (ans == Integer.MAX_VALUE)   return -1;
        return ans;
    }
    private void swap(int[] arr1, int[] arr2, int pos) {
        int temp = arr1[pos];
        arr1[pos] = arr2[pos];
        arr2[pos] = temp;
    } 
    private int getSwaps(int[] arr1, int[] arr2) {
        int swaps = 0, baseVal = arr1[0];
        for (int i = 1; i < arr1.length; i++) {
            if (arr1[i] != baseVal) {
                if (arr2[i] == baseVal) {
                    swaps++;
                } else return MAX;
            }
        }
        return swaps;
    }
}