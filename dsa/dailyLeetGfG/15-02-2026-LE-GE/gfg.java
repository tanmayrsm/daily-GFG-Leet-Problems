// User function Template for Java

class Solution {
    public int findMinDiff(ArrayList<Integer> arr, int m) {
        // your code here
        Collections.sort(arr);
        int n = arr.size(), ans = Integer.MAX_VALUE;
        for (int i = m - 1; i < n; i++) {
            int prev = arr.get(i), last = arr.get(i - (m - 1));
            ans = Math.min(ans, prev - last);
        }
        return ans;
    }
}