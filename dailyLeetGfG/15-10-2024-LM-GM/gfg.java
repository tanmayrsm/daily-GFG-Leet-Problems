
class Solution {
    // Function to count the number of subarrays which adds to the given sum.
    static int subArraySum(int arr[], int tar) {
        // add your code here
        int n = arr.length;
        int curr_sum = 0;
        int count = 0;
        HashMap<Integer, Integer> sum_freq = new HashMap<>();

        sum_freq.put(0, 1);

        for (int i = 0; i < n; i++) {
            curr_sum += arr[i];

            if (sum_freq.containsKey(curr_sum - tar)) {
                count += sum_freq.get(curr_sum - tar);
            }

            sum_freq.put(curr_sum, sum_freq.getOrDefault(curr_sum, 0) + 1);
        }

        return count;
        
    }
}