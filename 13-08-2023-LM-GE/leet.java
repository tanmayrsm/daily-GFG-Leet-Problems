class Solution {
    public boolean validPartition(int[] nums) {
        int n = nums.length;
        int current = 0, prev_pls_2 = 1, prev_pls_3 = 0;
        
        for(int curr = n - 2; curr >= 0; curr--) {
            int res1 = -1, res2 = -1, res3 = -1;

            // false out condition
            if(nums[curr + 1] - nums[curr] > 1) {
                prev_pls_3 = prev_pls_2;
                prev_pls_2 = current;
                current = 0;
                continue;
            }

            // try for all 2 same
            if(nums[curr] == nums[curr + 1])
                res1 = prev_pls_2;
            // try for all 3 same
            if(res1 != 1 && curr + 1 < n && curr + 2 < n && nums[curr] == nums[curr + 1] && nums[curr + 1] == nums[curr + 2])
                res2 = prev_pls_3;
            // try for 3 consecutive increasing elems
            if(res2 != 1 && curr + 1 < n && curr + 2 < n && nums[curr + 1] - nums[curr] == 1 && nums[curr + 2] - nums[curr + 1] == 1)
                res3 = prev_pls_3;

            int outcome = res1 == 1  || res2 == 1 || res3 == 1 ? 1 : 0;
            prev_pls_3 = prev_pls_2;
            prev_pls_2 = current;
            current = outcome;
        }
        return current == 1 ? true : false;
    }
}