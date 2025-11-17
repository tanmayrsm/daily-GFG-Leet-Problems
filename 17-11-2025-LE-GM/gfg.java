class Solution {
    public int maxSumIS(int arr[]) {
        // code here
        int n = arr.length, ans = 0;
        int[] ct = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            int maxo = 0;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] > arr[i])
                    maxo = Math.max(maxo, ct[j]);
            }
            ct[i] = arr[i] + maxo;
            ans = Math.max(ans, ct[i]);
        }
        return ans;
    }
}