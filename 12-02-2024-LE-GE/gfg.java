
//User function Template for Java

class Solution{
    static long sequence(int n){
        // code here
        long ans = 1, lastNo = 2, mod = 1000000007;
        for(int i = 1; i < n; i++) {
            // run n - 1 times
            long j = i + 1;
            int curr = 1;
            while(j-- > 0) {
                curr = (int)((curr * lastNo) % mod);
                lastNo++;
            }
            ans = (ans + Long.valueOf(curr)) % mod;
        }
        return ans % mod;
    }
}