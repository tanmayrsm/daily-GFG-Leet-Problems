
class Solution {
    int countFreq(int[] arr, int target) {
        // code here
        int l = binarySearch(arr, target, false);
        if (l == -1)    return 0;
        int r = binarySearch(arr, target, true);
        return r - l + 1;
    }
    private int binarySearch(int[] arr, int target, boolean goRight) {
        int l = 0, r = arr.length - 1, ans = -1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (arr[mid] == target) {
                ans = mid;
                if (!goRight)
                    r = mid - 1;
                else l = mid + 1;
            } else if (arr[mid] >= target) {
                r = mid - 1;
            } else l = mid + 1;
        }
        return ans;
    }
}