class Solution {
    public int maxSubarrayXOR(int[] arr, int k) {
        // code here
        int n = arr.length;
        int ans = 0, curr = 0;
        for (int i = 0; i < k; i++)
            ans ^= arr[i];
        curr = ans;
        for (int i = k; i < n; i++) {
            curr ^= arr[i - k];
            curr ^= arr[i];
            ans = Math.max(ans, curr);
        }
        return ans;
    }
}
