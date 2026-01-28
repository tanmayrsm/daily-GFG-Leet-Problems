//User function Template for Java

class Solution
{
    private static int[][] dp;
	public int maxDotProduct(int n, int m, int a[], int b[]) 
	{ 
		// Your code goes here
		dp = new int[n + 1][m + 1];
		for(int[] x : dp)
		    Arrays.fill(x, -1);
        return solve(0, 0, a, b);
	} 
    private static int solve(int n, int m, int[] a, int[] b) {
        if(m == b.length)   return 0;
        
        if(dp[n][m] != -1)  return dp[n][m];
        int take = 0, nottake = 0;
        
        take = a[n] * b[m] + solve(n + 1, m + 1, a, b);
        if(b.length - m < a.length - n)
            nottake = solve(n + 1, m, a, b);
        return dp[n][m] = Math.max(take, nottake);
    }
}
