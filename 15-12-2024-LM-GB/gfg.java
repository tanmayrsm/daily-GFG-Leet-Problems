class Solution {

    public int peakElement(int[] arr) {
        // code here
        int n = arr.length;
        if(n == 1)  return n - 1;
        if (arr[0] > arr[1])    return 0;
        int ans = 0;
        for(int i = 1; i < n - 1; i++) {
            if (arr[i] > arr[i - 1] && arr[i] > arr[i + 1]) return i;
        }
        return n - 1;
    }
}