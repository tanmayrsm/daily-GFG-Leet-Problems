
class Solution {
    static int gameOfXor(int N , int[] A) {
        // code here
        int ans = 0;
        for(int i = 1; i <= N; i++) {
            int elementsOnRight = N - i;
            int currElementXor = i + elementsOnRight *i;
            if(currElementXor % 2 != 0)  // odd occurence
                                         // even occurence -> cancelling effect
                ans ^= A[i - 1];
        }
        return ans;
    }
};