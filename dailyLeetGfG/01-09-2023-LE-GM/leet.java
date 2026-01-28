class Solution {
    public int[] countBits(int n) {
        int[] ans = new int[n + 1];
        for(int i = 0; i < n + 1; i++)
            ans[i] = getSetBits(i);
        return ans;
    }
    private static int getSetBits(int n) {
        int ct = 0;
        while(n > 0) {
            n = n & (n - 1);
            ct++;
        }
        return ct;
    }
}