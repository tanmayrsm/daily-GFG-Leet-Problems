class Solution {
    public int maxSum(int arr[]) {
        // code here
        int ans = 0;
        for (int i = 1; i < arr.length; i++) {
            ans = Math.max(ans, arr[i] + arr[i - 1]);
            if (i + 1 < n)
                ans = Math.max(ans, arr[i] + arr[i + 1]);
        }
        return ans;
    }
}
/*
[4, 3, 5, 1]

47 40 50 -> 50, 40 -> 90 & 47, 40 -> 87

47 40 1 50 1
maxSUm could be only 2 int
 */




