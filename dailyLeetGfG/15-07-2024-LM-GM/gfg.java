

class Solution {
    private static StringBuilder ans;
    private static int[] dp;
    public String smallestNumber(int s, int d) {
        // code here
        ans = new StringBuilder("-1");
        dp = new int[d];
        Arrays.fill(dp, -1);
        solve(s, d, 0, 0, new StringBuilder());
        return ans.toString();
    }
    private static int solve(int s, int d, int curr, int currSum, StringBuilder sb) {
        // System.out.println(curr + "::" + currSum + "::" + sb.toString());
        if (curr == d) {
            if (s == currSum) { 
                ans = new StringBuilder(sb);
                return currSum;
            }
            return -1;
        }
        if (dp[curr] != -1) return dp[curr];
        for (int i = (curr == 0 ? 1 : 0); i < 10; i++) {
            StringBuilder old = new StringBuilder(sb);
            sb.append(String.valueOf(i));
            int res = solve(s, d, curr + 1, currSum + i, sb);
            if (res != -1)  return res;
            sb = old;
        }
        return dp[curr] = -1;
    }
}


// Input: s = 9, d = 2
// Output: 18 
// Explanation: 18 is the smallest number possible with the sum of digits = 9 and total digits = 2.
// Input: s = 20, d = 3 
// Output: 299 
// Explanation: 299 is the smallest number possible with the sum of digits = 20 and total digits = 3.
// Expected Time Complexity: O(d)
// Expected Auxiliary Space: O(1)

// Constraints:
// 1 ≤ s ≤ 100
// 1 ≤ d ≤ 6



// s = 20, d = 3
// _ _ _
// 100  & 999

// 500 = mid
// not works => doesnt means I need to go on right