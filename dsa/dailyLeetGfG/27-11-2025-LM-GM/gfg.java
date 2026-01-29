class Solution {
    int[][] dp;
    int subsetXORSum(int arr[]) {
        // code here
        int sum = Arrays.stream(arr).sum(), n = arr.length;
        dp = new int[n][sum];
        for (int[] x : dp)  Arrays.fill(x, -1);
        return solve(arr, 0, 0);
    }
    private int solve(int[] arr, int curr, int last) {
        if (curr == arr.length) {
            return last;
        }
        if (dp[curr][last] != -1) return dp[curr][last];
        int x = solve(arr, curr + 1, arr[curr] ^ last);
        int y = solve(arr, curr + 1, last);
        return dp[curr][last] = x + y;
    }
}

//[1,2,3]
//t-> 1, no -> 0
//t -> 2, no -> 1^-


