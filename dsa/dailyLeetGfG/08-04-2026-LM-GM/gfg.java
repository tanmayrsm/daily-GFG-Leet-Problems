

class Solution {
    void segregate0and1(int[] arr) {
        // code here
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            while (left < right && arr[left] == 0) {
                left++;
            }
            while (left < right && arr[right] == 1) {
                right--;
            }
            if (left < right) {
                arr[left] = 0;
                arr[right] = 1;
                left++;
                right--;
            }
        }
    }
}