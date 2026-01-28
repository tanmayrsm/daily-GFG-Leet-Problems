class Solution {
    public long maxMatrixSum(int[][] matrix) {
        int negaCount = 0, maxNegativeNo = Integer.MIN_VALUE, minPositiveNo = Integer.MAX_VALUE;
        long ans = 0;
        for(int[] m : matrix) {
            for(int n : m) {
                if(n < 0) {
                    negaCount++;
                    maxNegativeNo = Math.max(maxNegativeNo, n);
                } else if (n >= 0) {
                    minPositiveNo = Math.min(minPositiveNo, n);
                }
            }
        }
        for(int[] m : matrix)
            for(int n : m) {
                ans += Math.abs(n);
            }
        if(negaCount % 2 == 1 && maxNegativeNo != Integer.MIN_VALUE) {
            if(minPositiveNo < Integer.MAX_VALUE)
                maxNegativeNo = Math.max(0 - minPositiveNo, maxNegativeNo);
            ans += maxNegativeNo * 2;   // why *2, cus I have added it in current ans, need to remove that added part AND remove negativeNo
        }
        
        return ans;
    }
}