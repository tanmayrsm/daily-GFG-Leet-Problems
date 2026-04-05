
class Solution {
    int countStrings(int n) {
        // code here
        // fibo type sum
        int a = 2;
        int b = 3,c;

        if (n == 1) {
            return 2;
        } else if (n == 2) {
            return 3;
        }

        for (int i = 3; i <= n; i++) {
            c = b;
            b = (a + b);
            a = c;
        }

        return b;
    }
    // private static long solve(int lastNo, int currPos, int n) {
    //     if(currPos >= n) return 1L;
    //     if(dp[currPos][lastNo + 1] != -1)   return dp[currPos][lastNo + 1];
    //     long take1 = 0, take0 = 0;
    //     if(lastNo == -1 || lastNo == 0) {
    //         take1 = solve(1, currPos + 1, n);
    //     }
    //     take0 = solve(0, currPos + 1, n);
    //     return dp[currPos][lastNo + 1] = (take0 + take1) % mod;
    // }
}