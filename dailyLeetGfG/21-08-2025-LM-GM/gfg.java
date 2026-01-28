class Solution {
    public int maxMinDiff(int[] arr, int k) {
        // code here
        Arrays.sort(arr);
        int l = 0, r = arr[arr.length - 1], ans = 0;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (isPossible(arr, k, mid)) {
                ans = Math.max(ans, mid);
                l = mid + 1;
            } else r = mid - 1;
        }
        return ans;
    }
    private boolean isPossible(int[] arr, int k, int maxDist) {
        int last = arr[0];
        k--;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] - last >= maxDist) {
                k--;
                last = arr[i];
            }
            if (k == 0) return true;
        }
        return false;
    }
}

// [1, 4, 9, 0, 2, 13, 3], k = 4
// max possible min distance
// 0 1 2 3 4 9 13

