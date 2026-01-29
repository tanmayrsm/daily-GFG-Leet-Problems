class Solution {
    public int maxCircularSum(int arr[]) {
        // code here
        int sum1 = 0, sum2 = 0;
        int maxi = Integer.MIN_VALUE, mini = Integer.MAX_VALUE;
        int total = 0, n = arr.length;

        for (int i = 0; i < n; i++) {
            total += arr[i];
            sum1 += arr[i];
            maxi = Math.max(sum1, maxi);
            sum1 = Math.max(sum1, 0);
            sum2 += arr[i];
            mini = Math.min(sum2, mini);
            sum2 = Math.min(sum2, 0);
        }
        if (total == mini) {
            return maxi;
        }
        return Math.max(maxi, total - mini);

    }
}