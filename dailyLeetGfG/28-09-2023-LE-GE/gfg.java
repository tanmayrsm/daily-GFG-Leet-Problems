
class Solution {
    public static void convertToWave(int n, int[] a) {
        // code here
        int l = 0, r = l + 1;
        while(r < n) {
            int temp = a[l];
            a[l] = a[r];
            a[r] = temp;
            l += 2;
            r += 2;
        }
    }
}
        
