class Solution {
    private static int[][] dp;
    public int minSteps(int n) {
        dp = new int[n][n];
        for (int[] d : dp)  Arrays.fill(d, -1);
        int res = solve(n, 1, 0);
        return res;
    }
    private static int solve (int n, int currN, int copied) {
        if (currN == n) return 0;
        else if (currN > n) return Integer.MAX_VALUE;

        int pasteAndCopy = Integer.MAX_VALUE, pasteOnly = Integer.MAX_VALUE;
        int r = 0;
        if (copied == 0)
            return 2 + solve(n, currN + 1, 1);
        r = solve(n, currN * 2, currN);
        if ( r != Integer.MAX_VALUE)
            pasteAndCopy = 2 + r;
        r = solve(n, currN + copied, copied);
        if (r != Integer.MAX_VALUE)
            pasteOnly = 1 + r;
        return Math.min(pasteAndCopy, pasteOnly);
        
    }
}