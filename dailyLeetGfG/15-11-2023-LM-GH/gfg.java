
class Solution {
    // WATCH AKSHAY ANILS VIDEO - to get no of distinct subsequences from a string
    public static String betterString(String str1, String str2) {
        // Code here
        int uniqueSubSeqS1 = uniqueSubseq(str1);
        int uniqueSubSeqS2 = uniqueSubseq(str2);
        return uniqueSubSeqS2 > uniqueSubSeqS1 ? str2 : str1;
    }
    private static int uniqueSubseq(String s) {
        int[] lastIndForChar = new int[26];
        Arrays.fill(lastIndForChar, -1);
        
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[0] = 1;
                
        for(int i = 1; i <= n; i++) {
            char ch = s.charAt(i - 1);
            int idx = ch - 'a';
            dp[i] = dp[i - 1] * 2;
            if(lastIndForChar[idx] != -1)
                dp[i] -= dp[lastIndForChar[idx]];
            lastIndForChar[idx] = i - 1;
        }
        return dp[n];
    }
}