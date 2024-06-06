
class Solution {    
    long max_sum(int[] a, int n) {
        // Initialize the current sum of elements and the initial configuration sum.
        long totalSum = 0;
        long currentConfigurationSum = 0;

        // Calculate the sum of all elements in the array and the initial configuration sum.
        for (int i = 0; i < n; i++) {
            totalSum += a[i];
            currentConfigurationSum += i * (long) a[i]; // Cast to long to prevent overflow
        }

        // Initialize the maximum sum to the initial configuration sum.
        long maxSum = currentConfigurationSum;

        // Iterate through the array to calculate the configuration sum for each rotation.
        for (int i = 1; i < n; i++) {
            // Calculate the next configuration sum using the previous configuration sum.
            currentConfigurationSum = currentConfigurationSum - totalSum + a[i - 1] * (long) n;
            // Update the maximum sum if the current configuration sum is greater.
            maxSum = Math.max(maxSum, currentConfigurationSum);
        }

        return maxSum;
    }
}
// 3 1 2 8
// 29 - 14 + [3 * 4]