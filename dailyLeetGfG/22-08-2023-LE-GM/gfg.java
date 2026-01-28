

class Solution {
    public static int findMinOperation(int N, int[][] matrix) {
        // code here
        
        // LOGIC - take out pen paper, and take a 3x3 matrix with all positive nos,
        // note that u can make sum of each row, col = MaxSum of each row, col
        // cus u cant decrease any no, u can only add, hence try making sum of each row/col = max sum (all row, all col)
        // find absolute difference of each row/col array whatever u have taken with maxSum
        // which is ur answer
        
        int[] arr = new int[N];
        int maxSum = Integer.MIN_VALUE;
        int ans = 0;
        // get maxsum from each row, ans construct array with rowSum
        // Note - u can also create array with colsum
        for(int i = 0; i < N; i++) {
            int s = 0;
            for(int j = 0; j < N; j++)
                s += matrix[i][j];
            arr[i] = s;
            maxSum = Math.max(maxSum , s);
        }
        
        // get max sum from each column
        for(int j = 0; j < N; j++) {
            int s = 0;
            for(int i = 0; i < N; i++)
                s += matrix[i][j];
            maxSum = Math.max(maxSum , s);
        }
        
        for(int i = 0; i < N; i++)
            ans += Math.abs(arr[i] - maxSum);
            
        return ans;
    }
}
        