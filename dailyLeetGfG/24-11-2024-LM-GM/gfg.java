class Solution {

    // arr: input array
    // Function to find the sum of contiguous subarray with maximum sum.
    int maxSubarraySum(int[] arr) {
        // Your code here
        int currSum = 0, maxSum = Integer.MIN_VALUE;
        for(int a : arr) {
            if(a + currSum < 0 || a > 0 && currSum < 0) {
                currSum = a; 
            } else {
                currSum += a;
            }
            maxSum = Math.max(maxSum, currSum);
        }
        return maxSum;
    }
}