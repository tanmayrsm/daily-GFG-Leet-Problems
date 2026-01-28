
class Solution {
    // Function to rotate an array by d elements in counter-clockwise direction.
    static void rotateArr(int arr[], int d) {
        // add your code here
        int n = arr.length;
        d = d %n;
        for(int i = 0; i < n; i++) {
            int nextPos = (i - d < 0) ? (i - d + n) : (i - d), next = arr[nextPos], current = arr[i];
            while(arr[i] >= 0 && arr[nextPos] >= 0) {
                int temp = next;
                arr[nextPos] = 0 - current;
                current = next;
                nextPos = (nextPos - d < 0) ? (nextPos - d + n) : nextPos - d;
                next = arr[nextPos];
            }
        }
        for(int i = 0; i < n; i++) 
            arr[i] = 0 - arr[i];
    }
}