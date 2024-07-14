
class Solution {
    void segregate0and1(int[] arr) {
        // code here
        int l = 0, r = 0, n = arr.length;
        while (r < n && l < n) {
            while (l < n && arr[l] == 0) l++;
            if (l < n && arr[l] == 1) {
                r = l + 1;
                while (r < n && arr[r] == 1) r++;
                if (r < n && arr[r] == 0) {
                    int temp = arr[l];
                    arr[l] = arr[r];
                    arr[r] = temp;
                    r++;
                }
            } else l++;

        }
    }
}
