
// User function Template for Java
class Solution {

    // arr: input array
    // Function to find the sum of contiguous subarray with maximum sum.
    long maxSubarraySum(int[] arr) {
        // Your code here
        long ans = Long.MIN_VALUE, curr = Long.MIN_VALUE, currMax = Long.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (curr == Long.MIN_VALUE)
                curr = arr[i];
            else if (arr[i] + curr < 0 || arr[i] > 0 && curr < 0)
                curr = arr[i];
            else 
                curr += arr[i];
            
            ans = Math.max(ans, curr);
        }
        return ans;
        
    }
}