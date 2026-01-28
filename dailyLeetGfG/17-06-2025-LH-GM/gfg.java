class Solution {
    public int minimumCoins(int[] arr, int k) {
        Arrays.sort(arr);
        int n = arr.length;
        int ans = Integer.MAX_VALUE;
        int totalLeft = 0;

        for (int i = 0; i < n; ++i) {
            totalLeft += (i > 0) ? arr[i-1] : 0;
            int removeRight = 0;
            int limit = arr[i] + k;

            int low = i, high = n - 1, pos = n;
            while (low <= high) {
                int mid = (low + high) / 2;
                if (arr[mid] > limit) {
                    pos = mid;
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }

            for (int j = pos; j < n; ++j) {
                removeRight += arr[j] - limit;
            }

            ans = Math.min(ans, totalLeft + removeRight);
        }

        return ans;
    }
}
// 1 40 50 90


// 1 4 1 2 4 1
// k or < k
// 1 1 1 2 5 5
// k = 3
// max diff = 4

// 1 1 1 2 5 5
// k = 0
// max diff = 4
//










