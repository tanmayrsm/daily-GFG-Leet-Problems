class Solution {
    public int maxXor(int[] a) {
        int n = a.length;
        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                res = Math.max(res, a[i] ^ a[j]);
            }
        }
        return res;
    }
}