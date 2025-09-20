class Solution {
    public static int longestSubarray(int[] arr) {
        // code here
        if (arr == null || arr.length == 0) return 0;


        int n = arr.length;
        int[] a = new int[n + 1];
        System.arraycopy(arr, 0, a, 0, n);
        a[n] = Integer.MAX_VALUE;

        int[] stack = new int[n + 1];
        int top = -1;
        int res = 0;

        for (int i = 0; i < a.length; i++) {
            while (top >= 0 && a[stack[top]] < a[i]) {
                int j = stack[top--];
                int prevIndex = (top >= 0) ? stack[top] : -1;
                int len = i - 1 - prevIndex;
                if (a[j] <= len) {
                    res = Math.max(res, len);
                }
            }
            stack[++top] = i;
        }

        return res;
    }
}