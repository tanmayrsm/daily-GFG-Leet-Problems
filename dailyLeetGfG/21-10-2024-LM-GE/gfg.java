
class Solution {

    public static int countgroup(int arr[]) {
        // find xor of all array elements
        // Complete the function
        long mod=1000000007;
        int allXor=0;
        int arrSize = arr.length;
        
        for(int ele:arr){
            allXor ^= ele;
        }
        
        if(allXor!=0)
            return 0;
    
        double count=((Math.pow(2, arrSize) -2)/2)%mod; //(2^n-2)/2
        return (int)count;
    }
}