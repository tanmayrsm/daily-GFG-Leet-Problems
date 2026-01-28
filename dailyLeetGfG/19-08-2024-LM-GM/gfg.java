class Solution {
    public static int kthSmallest(int[] arr, int k) {
        // Your code here
        int maxElem = Arrays.stream(arr).max().getAsInt(), currK = 1;
        int[] num = new int[maxElem];
        for (int a : arr) 
            num[a - 1]++;
        for (int i = 0; i < maxElem; i++) {
            if (num[i] != 0) {
                if (k == currK) return i + 1;
                currK++;
            }
        }
        return -1;
    }
}