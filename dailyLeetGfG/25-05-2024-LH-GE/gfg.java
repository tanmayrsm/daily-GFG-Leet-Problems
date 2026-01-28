class Solution {
    long max_Books(int arr[], int n, int k) {
        // Your code here
        long maxo = 0, ans = 0;
        for(int i = 0; i < n; i++) {
            if(arr[i] <= k)
                maxo += arr[i];
            else maxo = 0;
            ans = Math.max(ans, maxo);
        }
        return ans;
    }
}