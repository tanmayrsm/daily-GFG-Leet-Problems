
class Solution {
    private static int bs(int[] arr, int n, int l, int r, int x) {
        int lb = -1, ub = -1;
        while(l <= r) {
            int m = (l + r) / 2;
            if(arr[m] < x) {
                lb = m;
                l = m + 1;
            } else if (arr[m] >= x) {
                r = m - 1;
            }
        }
        
        l = 0; r = n - 1;
        while(l <= r) {
            int m = (l + r) / 2;
            if(arr[m] <= x) {
                l = m + 1;
            } else if (arr[m] > x) {
                ub =  m;
                r = m - 1;
            }
        }
        ub = ub == -1 ? n : ub;
        return ub - lb - 1;
    }
    int count(int[] arr, int n, int x) {
        // code here
        return bs(arr, n, 0, n - 1, x);
    }
}