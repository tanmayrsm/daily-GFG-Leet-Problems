class Solution {
    public int countSquare(int[][] mat, int x) {
        // code here
        int n = mat.length, m = mat[0].length, ans = 0;
        for(int i = 0; i < m; i++) {
            int[] res = new int[n];
            for(int j = i; j < m; j++) {
                for(int k = 0; k < n; k++) {
                    res[k] += mat[k][j];
                }
                // width of current submatrix
                int width = j - i + 1;
                if (width <= n)
                    ans += countArray(x, res, width); // changed: pass width
            }
        }
        return ans;
    }
    // changed signature: also take fixed length
    private int countArray(int target, int[] arr, int len) {

        // this method must count ONLY subarrays of length = len whose sum == target
        int n = arr.length;
        if (len > n) return 0;              // new line

        int ans = 0, sum = 0;               // changed: remove map, use sliding window
        for (int i = 0; i < n; i++) {
            sum += arr[i];                  // new / moved logic
            if (i >= len) sum -= arr[i - len]; // new line: maintain fixed window
            if (i >= len - 1 && sum == target) // new line: valid window
                ans++;
        }
        return ans;
    }
}

// 8 -8 8
// 8  0 8

// 4 2 -8 14, 6
// 4 6 -2 12

// 0 0 4 2 -8 14, 6
// 0 0 4 6 -2 12


