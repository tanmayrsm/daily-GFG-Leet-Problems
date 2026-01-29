class Solution {
    public int findSpecialInteger(int[] arr) {
        int n = arr.length, r = 1, ct, ans = 0, ctIndex = 0;
        while(r < n) {
            ct = 1;
            while(r - 1 >= 0 && r < n && arr[r] == arr[r - 1]) {
                r++;
                ct++;
            }

            if(ct> ans) {
                ans = ct;
                ctIndex = r - 1;
            }
            r++;
        }
        
        return ans != 0 ? arr[ctIndex] : arr[n - 1];
    }
}