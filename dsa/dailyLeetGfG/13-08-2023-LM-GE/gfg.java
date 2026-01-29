
//User function Template for Java
class Solution {
    static int nthFibonacci(int n){
        // code here
        int first = 1, second = 1, ans = 0, mod = 1000000007;
        if(n == 1 || n == 2)    return first;
        for(int i = 3; i <= n; i++) {
            ans = (first + second) % mod;
            second = first % mod;
            first = ans % mod;
        }
        return ans;
    }
}