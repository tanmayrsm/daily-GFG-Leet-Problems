class Solution {
    public int countOrders(int n) { // referred soln
        long res=1, mod=1000000007, u;
        for(int i=2;i<=n;++i) {
            u=(i-1)*2+1;
            res=u*(u+1)/2*res%mod;
        }
        return (int)res;
    }
}