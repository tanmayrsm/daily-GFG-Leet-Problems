class Solution {
    public int[] sumZero(int n) {
        int l = -1, r = 1, i = 0;
        int[] ans = new int[n];
        if (n % 2 == 1) i = 1;
        for (; i < n; i+=2) {
            ans[i] = l;
            ans[i + 1] = r;
            l--;
            r++;
        }
        return ans;
    }
}