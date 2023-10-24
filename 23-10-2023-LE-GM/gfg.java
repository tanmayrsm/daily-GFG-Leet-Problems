
class Solution
{
    public int maxSumIS(int arr[], int n)  // referred soln
	{  
	    //code here.
        int dp[] = new int[n];
        dp[0] = arr[0];
        int ret = dp[0];
        for(int i = 1; i < n; i++) {
            dp[i] = arr[i];
            for(int j = i - 1; j >= 0; j--) {
                if(arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[i], arr[i] + dp[j]);
                }
            }
            ret = Math.max(ret, dp[i]);
        }
        return ret;
	}   
    // private static int solve(int[] arr, int last, int curr) {
    //     if(curr >= arr.length) return 0;
    //     int take = Integer.MIN_VALUE, nottake = Integer.MIN_VALUE;
    //     if(dp[curr + 1][last + 1] != -1)    return dp[curr + 1][last + 1];
    //     if(last == -1 || arr[last] < arr[curr]) {
    //         int r = solve(arr, curr, curr + 1);
    //         if(r != Integer.MIN_VALUE)
    //             take = arr[curr] + r;
    //     }
    //     nottake = solve(arr, last, curr + 1);
    //     return dp[curr + 1][last + 1] = Math.max(take, nottake);
    // }  
}