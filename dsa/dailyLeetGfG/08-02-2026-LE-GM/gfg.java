class Solution {
    int maxProduct(int[] arr) {
        // code here
        int max = arr[0], min = arr[0], ans = max;
        for(int i = 1; i < arr.length; i++) {
            int a = arr[i];
            if(a < 0) {
                int temp = max;
                max = min;
                min = temp;
            }
            max = Math.max(a, max * a);
            min = Math.min(a, min * a);
            ans = Math.max(ans, max);
        }
        return ans;
    }
}


