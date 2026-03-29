

class Solution {
    private  int dp[][];
    private  int mod = 1000000007;
    public  int countPartitions(int[] arr, int d) {
        // code here
        int n = arr.length;
        int total = Arrays.stream(arr).sum();
        dp = new int[n + 1][total + 1];
        for(int[] x : dp)   Arrays.fill(x, -1);
        return solve(0, 0, d, arr, total);

    }
    private  int solve(int curr, int currSum, int d, int[] arr, int total) {
        if(curr == arr.length) {
            int rem = total - currSum;
            if(currSum - rem == d) return 1;
            return 0;
        }
        if(dp[curr][currSum] != -1) return dp[curr][currSum];
        int take = 0, nottake = 0;
        take = solve(curr + 1, currSum + arr[curr], d, arr, total);
        nottake = solve(curr + 1, currSum, d, arr, total);
        return dp[curr][currSum] = (take + nottake) % mod;
    }
}
        