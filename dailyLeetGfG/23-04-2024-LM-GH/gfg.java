// User function Template for Java
class Solution {    // referred soln
    static int firstElement(int n) {
        // code here
         if(n==1 || n==2) return 1;
        if(n==3) return 2;
        int a1=1, a2=2,a3=0;
        int i=4;
        while(i<=n){
            a3=(a1+a2)%1000000007;
            a1=a2;
            a2=a3%1000000007;
            i++;
        }
        return a3%(1000000007);
    }
}