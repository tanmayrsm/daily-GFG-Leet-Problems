class Solution {
    boolean[] prime;
    public int countPrimeSetBits(int left, int right) {
        int ans = 0;
        prime = new boolean[32];    // max 32 set bits for any integer constrait given
        Arrays.fill(prime, true);
        setPrimes();

        System.out.println();

        for(int i = left; i <= right; i++) {
            int no = i, sB = 0;
            while(no > 0) {
                int rightMostBit = no & 1;
                sB += rightMostBit;
                no >>= 1;
            }
            if(isPrime(sB)) {
                ans++;
            }
        }
        return ans;
    }
    private void setPrimes() {
        int n = prime.length;
        for(int i = 2; i * i < n; i++) {
            for(int j = i * i; j < n; j += i) {
                if(prime[j]) {
                    prime[j] = false;
                }
            }
        }
    }
    private boolean isPrime(int no) {
        if(no <= 1) return false;
        return prime[no];
    }
}