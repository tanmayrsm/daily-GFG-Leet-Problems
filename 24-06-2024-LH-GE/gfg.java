
// User function Template for Java

class Solution {
    static long sumMatrix(long n, long q) {
        // code here
        long a = Math.abs((n+1) - q);
        long ans = n-a;
        if(ans<0){
            return 0;
        }
        return ans;
    }
}