class Solution {
    public int minDaysBloom(int[] arr, int k, int m) {
        // code here
        int l = 1, r = Integer.MAX_VALUE, ans = Integer.MAX_VALUE;
        while (l < r) {
            int mid = (l + r) / 2;
            if (isValid(arr, k, m, mid)) {
                r = mid - 1;
                ans = Math.min(mid, ans);
            } else l = mid + 1;
        }
        return (ans == Integer.MAX_VALUE) ? -1 : ans;
    }
    private boolean isValid(int[] arr, int k, int m, int day) {
        int l = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > day) {
                if (l != -1) {
                    m -= ((i - 1) - l) / k;
                }
                l = -1;
            } else if (arr[i] <= day) {
                if (l == -1)    l = i;
            }
        }
        if (l != -1) {
            m -= ((n - 1) - l) / k;
        }
        return m <= 0;
    }
}