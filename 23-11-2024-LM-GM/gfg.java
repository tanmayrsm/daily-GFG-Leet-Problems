
// User function Template for Java
class Solution {
    public int getMinDiff(int k, int[] arr) {
        // code here
        // code hereint 
        int n = arr.length;
        if (n == 1) {
            return 0; // Single tower, no difference
        }
        
        // Sort the array
        Arrays.sort(arr);
        
        // Initial difference
        int initialDiff = arr[n - 1] - arr[0];
        int minDiff = initialDiff;
        
        // Base heights after adding/subtracting k
        int smallest = arr[0] + k;
        int largest = arr[n - 1] - k;
        
        // Traverse through the sorted array
        for (int i = 1; i < n; i++) {
            int minHeight = Math.min(smallest, arr[i] - k);
            int maxHeight = Math.max(largest, arr[i - 1] + k);
            minDiff = Math.min(minDiff, maxHeight - minHeight);
        }
        
        return minDiff;
    }
    
}
// 1     5      8    10, k = 4
// 5 (1,9) (4,12) (6,14)
// 5 9 4 6 => 9 - 4 => 5 