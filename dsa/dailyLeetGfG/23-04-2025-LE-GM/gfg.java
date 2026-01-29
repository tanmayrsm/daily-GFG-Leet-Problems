class Solution {
    public int[] singleNum(int[] arr) {
        // Code here
        // Code here
        int xor=0;
        for(var a: arr){
            xor^=a;
        }
        
        int setBitMask=0;
        for(int i=0;i<32;i++ ){
            if(((xor>>i)&1)==1){
                setBitMask= 1<<i;
                break;
            }
        }
        
        int zeroBit=0;
        int oneBit=0;
        for(int a:arr){
        if((a&setBitMask) == 0){
            zeroBit^=a;
        }
        else{
            oneBit^=a;
        }
        }
        
        int ans[]=new int[]{oneBit,zeroBit};
        Arrays.sort(ans);
        return ans;
    }
}