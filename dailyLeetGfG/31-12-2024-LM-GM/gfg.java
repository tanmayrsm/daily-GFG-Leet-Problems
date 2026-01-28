class Solution {

    // Function to return length of longest subsequence of consecutive integers.
    public int longestConsecutive(int[] arr) {
        // code here
        Arrays.sort(arr);
        int ans = 1, curr = 1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] - arr[i - 1] == 1) {
                curr++;
                ans = Math.max(ans, curr);
            } else if (arr[i] - arr[i - 1] > 1) curr = 1;
        }
        return ans;
    }
}