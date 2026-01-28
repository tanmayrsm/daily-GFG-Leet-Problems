
// User function Template for Java

class Solution {

    int Maximize(int arr[]) {
        // Complete the function
        final int mod=1000000007;
        Arrays.sort(arr);
        long sum=0L;
        for(int i=0;i<arr.length;i++){
         
            sum=(sum+(long)arr[i]*i)%mod;
        }
        return (int)sum;
    }
}
