class Solution {
    int countWays(int n, int k) {
        int prev2 = k;
        int prev1 = k * k;
        int curr;

        if(n == 1)
            return prev2;
        if(n == 2)
            return prev1;

        for(int i = 3; i <= n; i++) {
            int ans = (prev1 + prev2)*(k - 1);
            curr = ans;
            // Shift
            prev2 = prev1;
            prev1 = curr;
        }
        return prev1;
    }
}
