
class Solution {
    public static int bitMagic(int n, int[] arr) {
        // code here
        int l = 0;
        int r = n - 1;
        int ans = 0;
        while(l < r) {
            if(arr[l] != arr[r])
                ans++;
            l++;
            r--;
        }
        return ans % 2 == 0 ? ans / 2 : ans / 2  + 1;
    }
}
        
