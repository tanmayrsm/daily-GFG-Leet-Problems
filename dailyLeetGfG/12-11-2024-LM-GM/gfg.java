class Solution {
    static boolean canAttend(int[][] arr) {
        // Your code here
        Arrays.sort(arr, (int[] a, int[] b) -> {
            if (a[0] == b[0])
                return Integer.compare(a[1], b[1]);
            return Integer.compare(a[0], b[0]);
        });
        for(int i = 1; i < arr.length; i++) {
            if(arr[i][0] < arr[i - 1][1])
                return false;
        }
        return true;
    }
}