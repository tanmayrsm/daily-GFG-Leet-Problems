class Solution {
    public long findMaxProduct(List<Integer> arr) {
        long ans=1;
        int maxNeg=-10;
        for(int i:arr){
            if(i!=0){
                ans=(ans*i)%1000000007;
            }
            if(i<0 && maxNeg<i)maxNeg=i;
        }
        if(ans<0)return ans/maxNeg;
        return ans;
    }
}