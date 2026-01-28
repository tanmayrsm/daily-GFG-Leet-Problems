
class Solution{
    private static int wildN, patternN;
    private static int[][] dp;
    static boolean match(String wild, String pattern)
    {
        // code here
        wildN = wild.length();
        patternN = pattern.length();
        dp = new int[wildN + 1][patternN + 1];
        for(int[] x : dp)   Arrays.fill(x, -1);
        return solve(wild, pattern, 0, 0) == 1 ? true : false;
    }
    
    private static int solve(String wild, String pattern, int wildPtr, int patternPtr) {
        if(wildPtr == wildN && patternPtr == patternN) return 1;
        if(wildPtr >= wildN || patternPtr >= patternN)  return 0;
        char wildc = wild.charAt(wildPtr);
        char patternc = pattern.charAt(patternPtr);
        boolean isNormalCharWild = wildc != '*' && wildc != '?';
        int finalVal = 0;

        if(dp[wildPtr][patternPtr] != -1)   return dp[wildPtr][patternPtr];

        if(isNormalCharWild) {
            //both have to be same
            if(wildc == patternc)
                finalVal = solve(wild, pattern, wildPtr + 1, patternPtr + 1);
            // both not same
            else finalVal = 0;
        }

        if(wildc == '*') {
            // take current in pattern
            int take = solve(wild, pattern, wildPtr, patternPtr + 1);
            // dont take current '*' in pattern, compare next in wild
            int nottake = solve(wild, pattern, wildPtr + 1, patternPtr);
            //take both and move
            int takeBoth = solve(wild, pattern, wildPtr + 1, patternPtr + 1);
            finalVal = (take==1 || nottake==1 || takeBoth==1) ? 1 : 0;
        }
        if(wildc == '?') {
            finalVal = solve(wild, pattern, wildPtr + 1, patternPtr + 1);
        }
        return dp[wildPtr][patternPtr] = finalVal==1 ? 1 : 0;
    }
}