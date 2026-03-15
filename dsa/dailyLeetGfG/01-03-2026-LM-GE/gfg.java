class Solution {
    void pushZerosToEnd(int[] arr) {
        // code here
        int n = arr.length, r = 0, l = 0;
        while(r < n) {
            //l finds 0
            while(l < n && arr[l] > 0)  l++;
            r = Math.max(r + 1, l + 1);
            while(r < n && arr[r] == 0) r++;
            if(l < r && r < n && arr[l] == 0 && arr[r] != 0) {
                int temp = arr[l];
                arr[l] = arr[r];
                arr[r] = temp;
            }
            l++;
        }
    }
}