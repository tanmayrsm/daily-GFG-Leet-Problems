
//User function Template for Java

class Solution {
    static String longestSubstring(String s, int n) {
        // code here
        // find all substrings
            // n^2
            // for each substring, find its occurences in whole string
            // takes rabinkarp -> extra O(n)
        //hence overall complexity -> O(n^3)

        // if(n == 1)  return "-1";
        // better approach -> dp tabulation
        int[][] dp = new int[n + 1][n + 1]; int maxLen = 0;
        for(int i = 1; i <= n; i++) {
            for(int j = i + 1; j <= n; j++) {
                int ct = 0;
                if(s.charAt(i - 1) == s.charAt(j - 1))
                    ct = 1 + dp[i - 1][j - 1];
                
                // edge case
                if(j - i >= ct)  // ct is length, so diff of j and i has to be more than ct, to ensure no overlap
                    dp[i][j] = ct;
                maxLen = Math.max(maxLen, dp[i][j]);
            }
        }
        if(maxLen == 0) return "-1";
        
        StringBuilder sb = new StringBuilder();
        for(int i = n; i >= 0; i--)
            for(int j = n; j >= 0; j--) {
                if(dp[i][j] == maxLen) {
                    sb = new StringBuilder();
                    int I = i, J = j;
                    while(I >= 0 && J >= 0 && dp[I][J] > 0) {
                        sb.append(s.charAt(I - 1) + "");
                        I--;
                        J--;
                    }
                    sb.reverse();
                    // return sb.toString(); ... we want first occured maxLen string, hence wont return here
                }
            }
        if(sb.length() > 0) return sb.toString();
        return "-1";

    }
}


// heheheh

// for i = 0 to n
//      test(i, s(i : ))

// test(k, mainS)
    // s = mainS(k : )
    // len = len(mainS)
    // from i = 0 to len/2
        // str = s(i : len/2 - i)  // max string formed
        // int ans = find(s, str)
        // if ans > 1   return ans


// find(s, str) :
//      int first = rabinkarp(s, str)   // gives first index of str occurence in s
//      int ans = 0
//      if(first != -1)
//          ans = 1 + find(s(first + 1 : ), str)
//      return ans

// O(n^3)
