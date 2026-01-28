class Solution {
    public int maxSubarraySum(int[] arr, int k) {
        // Code here
        int n = arr.length;
        int sum = 0, cSum = 0;
        for(int i = 0; i < k; i++)  sum += arr[i];
        cSum = sum;
        for (int i = k; i < n; i++) {
            cSum += arr[i];
            cSum -= arr[i - k];
            sum = Math.max(sum, cSum);
        }
        return sum;
    }
}