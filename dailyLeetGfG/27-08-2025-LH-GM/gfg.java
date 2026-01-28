class Solution {
    public int countTriangles(int arr[]) {
        // code here
        Arrays.sort(arr);
        int n = arr.length, ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int req = findNoLessThan(arr, arr[i] + arr[j], j + 1, n - 1);
                if (req > -1) {
                    ans += req - j;
                }
            }
        }
        return ans;
    }
    private int findNoLessThan(int[] arr, int k, int l, int r) {
        int idx = -1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (arr[mid] < k) {
                idx = mid;
                l = mid + 1;
            } else r = mid - 1;
        }
        return idx;
    }
}