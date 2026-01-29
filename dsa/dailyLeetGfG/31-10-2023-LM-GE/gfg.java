
class Solution {
    void pushZerosToEnd(int[] arr, int n) {
        // code here
        // two pointers,
        // l -> points to zero from left
        // r -> points to non zero after - l + 1

        // go until l < n && r < n

        int l = 0, r = -1;
        while (l < n && r < n) {
            while(l < n && arr[l] != 0) {
                l++;
            }
            if(r == -1)
                r = l + 1;
            while(r < n && arr[r] == 0) {
                r++;
            }
            if(l < n && r < n) {
                int temp = arr[l];
                arr[l] = arr[r];
                arr[r] = temp;
            }
            r++;
            l++;
        }

    }
}