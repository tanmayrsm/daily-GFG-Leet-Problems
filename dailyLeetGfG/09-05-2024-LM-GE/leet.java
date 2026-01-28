class Solution {
    public long maximumHappinessSum(int[] happiness, int k) {
        long ans = 0, K = k;
        int n = happiness.length;
        Arrays.sort(happiness);
        for(int i = n - 1; i >= 0 && k > 0; i--) {
            if(happiness[i] - (K - k) >= 0)
                ans += happiness[i] - (K - k);
            k--;
        }
        return ans;
    }
}