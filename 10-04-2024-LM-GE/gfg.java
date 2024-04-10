
//User function Template for Java

class Solution{
    static int findSingle(int n, int arr[]){
        // code here
        int ans = 0;
        for(int x : arr)    ans ^= x;
        return ans;
    }
}