class Solution {
    public long maximumHappinessSum(int[] arr, int k) {
        Arrays.sort(arr);
        long ct = 0, ans = 0;
        int n = arr.length;
        for (int i = n - 1; i >= 0 && k-- > 0; i--) {
            long curr = arr[i] - ct;
            if (curr <= 0)    return ans;
            ans += curr;
            ct++;
        }
        return ans;
    }
}