
//User function Template for Java


class Solution
{
    private static int[] dp;
    //Function to find the maximum number of cuts.
    public int maximizeCuts(int n, int x, int y, int z)
    {
       //Your code here
       dp = new int[n + 1];
       Arrays.fill(dp, -1);
       int ans = solve(n, x, y, z);
       return ans == Integer.MIN_VALUE ? 0 : ans;

    }
    private static int solve (int currN, int x, int y, int z) {
        if (currN == 0) return 0;
        if (dp[currN] != -1)    return dp[currN];
        int currX = Integer.MIN_VALUE, currY = Integer.MIN_VALUE, currZ = Integer.MIN_VALUE;
        if (currN - x >= 0) {
            int temp = solve(currN - x, x,y,z);
            if (temp != Integer.MIN_VALUE)
                currX = 1 + temp;
        }
        if (currN - y >= 0) {
            int temp = solve(currN - y, x,y,z);
            if (temp != Integer.MIN_VALUE)
                currY = 1 + temp;
        }
        if (currN - z >= 0) {
            int temp = solve(currN - z, x,y,z);
            if (temp != Integer.MIN_VALUE)
                currZ = 1 + temp;
        }
        return dp[currN] = Math.max(currX, Math.max(currY, currZ));
    }
}