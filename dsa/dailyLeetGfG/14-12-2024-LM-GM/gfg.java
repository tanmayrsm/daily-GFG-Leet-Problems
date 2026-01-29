
class Solution {
    int search(int[] arr, int key) {
        // Complete this function
        int n = arr.length, l = 0, r = n - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (arr[mid] == key)    return mid;
            // left side balanced
            if (arr[l] <= arr[mid]) {
                if (arr[l] <= key && arr[mid] >= key)
                    r = mid - 1;
                else l = mid + 1;
            } else {
                if (arr[r] >= key && arr[mid] <= key)
                    l = mid + 1;
                else r = mid - 1;
            }
        }
        return -1;
    }
}