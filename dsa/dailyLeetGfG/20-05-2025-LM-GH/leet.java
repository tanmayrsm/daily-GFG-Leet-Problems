class Solution {
    public boolean isZeroArray(int[] nums, int[][] queries) {
        int n = nums.length;
        int[] diff = new int[n + 1];

        for (int[] q : queries) {
            diff[q[0]]++;
            if (q[1] + 1 < diff.length) {
                diff[q[1] + 1]--;
            }
        }

        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += diff[i];
            if (nums[i] <= sum) {
                nums[i] = 0;
            } else {
                return false;
            }
        }

        return true;
    }
}

//[4,3,2,1], queries = [[1,3],[0,2]]
//[0,0,0,0,0]
//[0,-1,0,0,1]
//[-1,-1,0,1,1]
//[-1,-2,-2,-1,0]
//[3,1,0,0]