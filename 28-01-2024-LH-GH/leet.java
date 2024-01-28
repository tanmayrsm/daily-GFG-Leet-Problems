class Solution {
    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        int n = matrix.length, m = matrix[0].length, ans = 0;
        for(int i = 0; i < m; i++) {
            int[] arr = new int[n];
            for(int j = i; j < m; j++) {
                for(int k = 0; k < n; k++)
                    arr[k] += matrix[k][j];
                ans += getSubArrays(arr, target);
            }
        }
        return ans;
    }
    private static int getSubArrays(int[] arr, int target) {
        int ans = 0, n = arr.length;
        for(int i = 0; i < n; i++) {
            int currSum = 0;
            for(int j = i; j < n; j++) {
                currSum += arr[j];
                if(currSum == target)   ans++;
            }
        }
        return ans;
    }
}