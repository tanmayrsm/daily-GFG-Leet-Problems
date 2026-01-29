
// User function Template for Java
class Solution {
    static int numberOfConsecutiveOnes(int n) { // reff
        // code here
        int MOD = (int)1e9 +7;
        
        int prevFib = 1, curFib = 1;
        int cur = 1;
        
        for(int i=3; i<=n; i++){
            cur =  ((cur*2)%MOD + curFib)%MOD;
            int temp = prevFib;
            prevFib = curFib;
            curFib = (curFib + temp)%MOD;
        }
        
        return cur;
    }
}