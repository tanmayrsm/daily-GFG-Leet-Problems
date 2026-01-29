class Solution {
    public void sort012(int[] arr) {
        // code here
        int n = arr.length;
        int l = 0, mid = 0, r = n - 1;
        while (mid <= r) {
            if (arr[mid] == 2) {
                swap(arr, mid, r);
                r--;
            } else if (arr[mid] == 0) {
                swap(arr, mid, l);
                l++;
                mid++;
            } else {
                mid++;
            }
        }       
    }
    private void swap(int[] arr, int x, int y) {
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }
}