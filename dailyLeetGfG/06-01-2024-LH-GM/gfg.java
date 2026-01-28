
class Solution {
    public static long sumOfPowers(long a, long b) {    // reff soln
        // code here
        int[] p=new int[(int)(b+1)];
        for(int i=2;i<=b;i++){
            if(p[i]==0){
                for(int j=i;j<=b;j+=i){
                    p[j]=i;
                }
            }
        }
        // for(int x : p)
        //     System.out.print("::" + x);
        int ans=0;
        for(int i=(int)(a);i<=b;i++){
            int x=i;
            while(x>1){
                x/=p[x];
                ans++;
            }
        }
        return ans;
    }
}
        