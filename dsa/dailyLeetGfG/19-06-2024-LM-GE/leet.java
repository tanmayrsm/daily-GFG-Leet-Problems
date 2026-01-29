import java.util.Arrays;

class Solution {
    public int minDays(int[] arr, int m, int k) {
        int n = arr.length;
        if (m * k > n)  return -1;
        int maxo = Arrays.stream(arr).max().getAsInt();
        int l = 1, r = maxo, ans = -1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (getSum(mid, arr, m, k)) {
                r = mid - 1;
                ans = mid;
            } else {
                l = mid + 1;
            }
        }
        return ans;
    }
    private static boolean getSum(int maxDays, int[] arr, int m, int k) {
        int ct = 0;
        for(int i = 0;  i < arr.length; i++) {
            if (m == 0) return true;
            if(arr[i] <= maxDays) {
                ct++;
                if(ct == k) {
                    m--;
                    ct = 0;
                }
            } else {
                ct = 0;
            }
        }
        return m <= 0;
    }
}