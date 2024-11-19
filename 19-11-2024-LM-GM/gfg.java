class Solution {
    void nextPermutation(int[] arr) {
        // code here
        int n = arr.length, r = n - 2;
        while (r >= 0 && arr[r] >= arr[r + 1]) {
            r--;
        }
        if(r == -1) {
            Arrays.sort(arr);
        } else {
            int biggerAndMin = Integer.MAX_VALUE, indo = -1;
            for(int i = r + 1; i < n; i++)
                if(arr[i] > arr[r] && arr[i] < biggerAndMin) {
                    indo = i;
                    biggerAndMin = arr[i];
                }     
            if(biggerAndMin != Integer.MAX_VALUE) {
                int temp = arr[r];
                arr[r] = biggerAndMin;
                arr[indo] = temp;
                Arrays.sort(arr, r + 1, n);

            }

        }
    }
}