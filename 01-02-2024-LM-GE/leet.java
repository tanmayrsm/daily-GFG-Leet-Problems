class Solution {
    public int[][] divideArray(int[] nums, int k) {
        int n = nums.length;
        int[][] ans = new int[n / 3][3];
        // sorting will help
        Arrays.sort(nums);
        int curr = 0, l = 0, r = 0;
        boolean done = true;
        while(curr < n && done) {
            r = 0;
            int currLow = nums[curr];
            while(r < 3 && curr < n && nums[curr] - currLow <= k) {
                ans[l][r] = nums[curr];
                curr++;
                r++;
            }
            if(r != 3)  done = false;
            l++;
        }
        if(!done)   return new int[0][0];
        return ans;
    }
}