class Solution {
    public boolean[] isArraySpecial(int[] nums, int[][] queries) {
        int n = nums.length, m = queries.length;
        int[] ct = new int[n];
        boolean[] ans = new boolean[m];
        for(int i = 1; i < n; i++) {
            if((nums[i] % 2 == 0 && nums[i - 1]%2 != 0) || (nums[i] % 2 != 0 && nums[i - 1]%2 == 0))
                 ct[i] = 1 + ct[i - 1];
            else ct[i] = ct[i - 1];
        }
        for(int i = 0; i < m; i++) {
            ans[i] = (ct[queries[i][1]] - ct[queries[i][0]]) == 0 ? true : false;
        }
        return ans;
    }
}

// [4,3,1,6]
// [_,0,1,1]
// q = [2,3]

// [3,4,1,2,6]
// [_,0,0,0,1]
// q = [0, 4]
