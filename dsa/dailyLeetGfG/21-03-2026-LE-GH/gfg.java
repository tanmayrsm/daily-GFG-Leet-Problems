class Solution {
    static Integer[] dp = new Integer[16];
    static int precompute(int n) {
        if(n == 0) return 1;
        if(dp[n] != null) return dp[n];

        int ans = 0;
        for(int i = 1; i <= n; i++) {
            ans += precompute(i - 1) * precompute(n - i);
        }
        return dp[n] = ans;
    }
    static {
        dp[0] = 1;
    }
    public ArrayList<Integer> countBSTs(int[] arr) {
        // Code here
        precompute(15);
        int n = arr.length;
        int[] less = new int[16];
        ArrayList<Integer> ans = new ArrayList<>();

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(arr[j] < arr[i]) less[arr[i]]++;
            }
            int left = dp[less[arr[i]]];
            int right = dp[n - less[arr[i]] - 1];
            ans.add(left * right);
        }
        return ans;

    }
}