
// User function Template for Java

class Solution {
    int search(int[] arr, int key) {
        // Complete this function
        int l = 0, r = arr.length - 1;
        while(l <= r) {
            int mid = (l + r) / 2;
            if (arr[mid] == key)    return mid;
            if (arr[l] <= arr[mid]) {   // left side sorted
                if (arr[l] <= key && arr[mid] >= key) {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            } else {        // right side sorted
                if (arr[mid] <= key && arr[r] >= key) {
                    l = mid;
                } else {
                    r = mid - 1;
                }
            }
        }
        return -1;

    }
}