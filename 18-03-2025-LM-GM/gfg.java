private static int[][] dp;
    static int equalPartition(int N, int arr[])
    {
        // code here
        // to part array into two parts, sum1 = (sum of all elemns) / 2
        // hence u need to find a non-contiguous subarray with sum sum1
        // which proves that another part does lies with sum2 => sum - sum1
        // sum = sum1 + sum2
        // sum1 = sum/2 & sum2 = sum/2

        int sum = Arrays.stream(arr).sum();
        if(sum % 2 != 0)    return 0;
        int searchForSum = sum / 2;
        dp = new int[N + 1][searchForSum + 1];
        for(int[] x :  dp)  Arrays.fill(x, -1);
        return solve(arr, 0, searchForSum);
    }

    private static int solve(int[] arr, int index, int sum) {
        if(sum == 0)    return 1;
        if(index >= arr.length)  return 0;
        if(dp[index][sum] != -1)    return dp[index][sum];
        
        int take = 0, nottake = 0;
        if(sum - arr[index] >= 0) {
            take = solve(arr, index + 1, sum - arr[index]);
        }
        nottake = solve(arr, index + 1, sum);
        return dp[index][sum] = take == 1 || nottake == 1 ? 1 : 0;
    }