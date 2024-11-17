
class Solution {
    public void reverseArray(int arr[]) {
        // code here
        int l = 0, r = arr.length - 1;
        while(l < r) {
            int temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            l++;
            r--;
        }
    }
}