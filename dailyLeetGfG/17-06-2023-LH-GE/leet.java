class Solution {    // referred soln
    public int makeArrayIncreasing(int[] arr1, int[] arr2) {
        TreeSet<Integer> set = new TreeSet<>();
        for (int i : arr2) set.add(i);
        
        int m = arr1.length;
        int p = Math.min(m, arr2.length);   // maximum # of operation we may perform;
        int[][] dp = new int[m][p + 1];
        for (int[] i : dp) Arrays.fill(i, Integer.MAX_VALUE);
        
        // Base Case: first column (0 swap)
        dp[0][0] = arr1[0];
        for (int i = 1; i < m; i++) {
            if (arr1[i] <= arr1[i-1]) break;
            dp[i][0] = arr1[i];
        }
        
        // Base Case: first row (only 1 character)
        for (int j = 1; j <= p; j++) {
            dp[0][j] = Math.min(arr1[0], set.first());
        }
        
       
        for (int i = 1; i < m; i++) {
            for (int j = 1; j <= Math.min(i+1, p); j++) {
              
                // not swap at i
                if (arr1[i] > dp[i-1][j]) dp[i][j] = arr1[i];   // handle the case dp[i-1][j] == MAX
                
                // swap at i
                if (dp[i-1][j-1] == Integer.MAX_VALUE) continue;  // this line will speed up the solution
                
                Integer temp = set.higher(dp[i-1][j-1]);
                if (temp != null) {
                    dp[i][j] = Math.min(dp[i][j], temp);
                }
            }
        }
        
        // check result for arr1 := arr1[0: m-1]
        for (int i = 0; i <= p; i++) {
            if (dp[m-1][i] != Integer.MAX_VALUE) return i;
        }
        return -1;
    }
}