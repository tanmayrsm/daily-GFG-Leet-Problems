class Solution {
    static final int MOD = 1_000_000_007;

    public int derangeCount(int n) {
        if (n == 0) return 1;
        if (n == 1) return 0;
        if (n == 2) return 1;

        long prev2 = 0; // D(1)
        long prev1 = 1; // D(2)

        for (int i = 3; i <= n; i++) {
            long curr = (long)(i - 1) * ((prev1 + prev2) % MOD) % MOD;
            prev2 = prev1;
            prev1 = curr;
        }

        return (int)(prev1 % MOD);
    }
}