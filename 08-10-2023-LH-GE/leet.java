class Solution {
    class Solution {
        static int[][][] dp;
        static int n, m;
        public int maxDotProduct(int[] nums1, int[] nums2) {
            n = nums1.length; m = nums2.length;
            
            dp = new int[nums1.length][nums2.length][2];
            for(int[][] x : dp)
                for(int[] y : x)
                    Arrays.fill(y, -1);
            return solve(nums1, nums2, 0, 0, 0) ;
        }
        private static int solve(int[] nums1, int[] nums2, int lastI, int lastJ, int picked) {
            if(lastI >= nums1.length || lastJ >= nums2.length) {
                if(picked == 0) return Integer.MIN_VALUE;  
                return 0;
            }
    
            if(dp[lastI][lastJ][picked] != -1)  return dp[lastI][lastJ][picked];
            int nos = Integer.MIN_VALUE;
            int take = nums1[lastI] * nums2[lastJ] + solve(nums1, nums2, lastI + 1, lastJ + 1, 1);
            int moveFirst = solve(nums1, nums2, lastI + 1, lastJ, picked);
            int moveSecond = solve(nums1, nums2, lastI, lastJ + 1, picked);
            int moveBoth = solve(nums1, nums2, lastI + 1, lastJ + 1, picked);
            
            return dp[lastI][lastJ][picked] = Math.max(take, Math.max(moveFirst, Math.max(moveSecond, moveBoth)));
        }
    }
}