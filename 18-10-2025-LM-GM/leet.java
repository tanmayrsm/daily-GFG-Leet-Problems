class Solution {
    public int maxDistinctElements(int[] nums, int k) {
        // Sort the array so we can process numbers in increasing order
        // This helps to avoid overlapping ranges and to maximize distinct elements
        Arrays.sort(nums); // O(n log n)

        int count = 1; // At least one distinct element can always be formed

        // Start with the smallest possible value for the first element
        int prev = nums[0] - k;

        // Iterate through the rest of the array
        for (int i = 1; i < nums.length; i++) { // O(n)
            int min = nums[i] - k; // Minimum possible value for current number
            int max = nums[i] + k; // Maximum possible value for current number

            if (min > prev) {
                // If the current range starts after 'prev',
                // we can safely take the smallest value (min) to keep distinctness
                count++;
                prev = min;
            }
            else if (prev < max) {
                // If 'prev' lies within the current range,
                // we can increment it by 1 to stay distinct but still valid
                prev = prev + 1;
                count++;
            }
            else {
                // If even the maximum allowed value is <= prev,
                // we can’t make a distinct element — skip this number
                continue;
            }
        }

        // Return total distinct numbers we could form
        return count;
    }
}