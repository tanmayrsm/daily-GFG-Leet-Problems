class Solution {
    int[] arr = new int[46];

    public int climbStairs(int n) {
        if(n == 1 || n == 2) {      
            arr[n] = n;
        }
        if(arr[n] != 0) {
            return arr[n];
        }
        arr[n] =  climbStairs(n-1) + climbStairs(n-2);
        return arr[n];
    }
}