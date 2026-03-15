class Solution {
    public int kBitFlips(int[] arr, int k) {
        // code here
        int n = arr.length, p = 0, ans = 0;
        int[] pref = new int[n];
        for (int i = 0; i < n; i++) {
            p += pref[i];
            if (arr[i] == 0 && p % 2 == 0 || arr[i] == 1 && p % 2 == 1) {
                pref[i]++;
                if (i + k < n)
                    pref[i + k]--;
                else if (i + k - 1 >= n) return -1;
                p++;
                ans++;
            }
        }
        return ans;

    }
}