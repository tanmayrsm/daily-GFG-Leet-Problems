class Solution {
    static int mod = 1000000007;
    static int[] map = new int[31];
    static {
        int[] prime = new int[] {2,3,5,7,11,13,17,19,23,29};
        for(int i = 2; i <= 30; i++) {
            if(i % 4 == 0 || i % 9 == 0 || i == 25)
                continue;
            int mask = 0;
            for(int j = 0; j < 10; j++)
                if(i % prime[j] == 0)
                    mask |= 1 << j;
            map[i] = mask;
        }
    }

    public int goodSubsets(int[] arr, int n) {
        int one = 0;
        int[] dp = new int[1024], cnt = new int[31];
        dp[0] = 1;
        for(int i : arr) {
            if(i == 1)
                one++;
            else if(map[i] != 0)
                cnt[i]++;
        }
        for(int i = 0; i < 31; i++) {
            if(cnt[i] == 0)
                continue;
            for(int j = 0; j < 1024; j++) {
                if((j & map[i]) != 0)
                    continue;
                dp[j | map[i]] = (int)((dp[j | map[i]] + dp[j] * (long)cnt[i]) % mod);
            }
        }
        long res = 0;
        for(int i : dp)
            res = (res + i) % mod;
        res--;
        if(one != 0)
            res = res * pow(one) % mod;
        return (int) res;
    }
    public long pow(int n) {
        long res = 1, m = 2;
        while(n != 0) {
            if((n & 1) == 1)
                res = (res * m) % mod;
            m = m * m % mod;
            n >>= 1;
        }
        return res;
    }
}