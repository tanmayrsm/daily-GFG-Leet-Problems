class Solution {
    private static int bs(int[] arr, int l, int r) {
        if(l > r)
            return -1;
        int mid = (l + r) / 2;
        if(arr[mid] > arr[mid + 1] && arr[mid - 1] < arr[mid])
            return mid;
        else if (arr[mid] < arr[mid + 1])
            return bs(arr, mid + 1, r);
        else    return bs(arr, l, mid - 1);
    }
    public int peakIndexInMountainArray(int[] arr) {
        return bs(arr, 0, arr.length - 1);
    }
}