
class Solution {
    public static int totalCuts(int N, int K, int[] A) {
        // code here
        int[] largestFromLeft = new int[N];
        int[] smallestFromRight = new int[N]; 
        int ans = 0;
        largestFromLeft[0] = A[0];
        smallestFromRight[N - 1] = A[N - 1];
        
        for(int i = 1; i < N; i++)
            largestFromLeft[i] = Math.max(largestFromLeft[i - 1], A[i]);
        
        for(int i = N - 2; i >= 0; i--)
            smallestFromRight[i] = Math.min(smallestFromRight[i + 1], A[i]);
            
        for(int i = 1; i < N; i++) 
            if(largestFromLeft[i - 1] + smallestFromRight[i] >= K)
                ans++;
                
        return ans;
        
    }
}