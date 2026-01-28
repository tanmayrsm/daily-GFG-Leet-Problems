
class Solution{
  static int[][] dp;
  static int knapSack(int N, int W, int val[], int wt[])
  {
      // code here
      dp = new int[N + 1][W + 1];
      // for(int[] x : dp)
      //   Arrays.fill(x, -1);

      for(int curr = N - 1; curr >= 0; curr--) {
        for(int w = 0; w <= W; w++) {
          int take = Integer.MIN_VALUE, nottake = take, stay = take;
          if(w - wt[curr] >= 0) {
            take = val[curr] + dp[curr + 1][w - wt[curr]];
            stay = val[curr] + dp[curr][w - wt[curr]];
          }
          nottake = dp[curr + 1][w];
          dp[curr][W] = Math.max(take, Math.max(nottake, stay));
        }
      }

      for(int i = 0; i < N; i++) {
        for(int j = 0; j < W + 1; j++) 
          System.out.print(dp[i][j] + " :: ");
        System.out.println();
      }

      return dp[0][0];
  }

  // private static int solve(int W, int[] val, int[] wt, int curr) {
  //   if(curr >= val.length)  return 0;
  //   if(dp[curr][W] != -1) return dp[curr][W];
    
  //   int take = Integer.MIN_VALUE, nottake = take, stay = take;
  //   if(W - wt[curr] >= 0) {
  //     take = val[curr] + solve(W - wt[curr], val, wt, curr + 1);
  //     stay = val[curr] + solve(W - wt[curr], val, wt, curr);
  //   }
  //   nottake = solve(W, val, wt, curr + 1);
  //   return dp[curr][W] = Math.max(take, Math.max(nottake, stay));
  // }

}