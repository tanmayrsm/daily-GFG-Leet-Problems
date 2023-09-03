class Solution
{
    static boolean[] prime;
    static int[] leastP;
    public int[] leastPrimeFactor(int n)
    {
        // code here
        sieve(n);
        
        int[] ans = new int[n + 1];
        ans[0] = 0;
        ans[1] = 1;
        
        for(int i = 2; i < n + 1; i++) {
            ans[i] = leastP[i] == 0 ? i : leastP[i];
        }
        
        return ans;
        
    }
    private static void sieve(int n) {
        prime = new boolean[n + 1];
        leastP = new int[n + 1];
        
        Arrays.fill(prime, true);
        
        for(int i = 2; i * i <= n; i++) {
            if(prime[i]) {
                for(int j = i * i; j <= n; j += i) {
                    prime[j] = false;
                    if(leastP[j] == 0)
                        leastP[j] = i;
                }
            }
        }
    }
}