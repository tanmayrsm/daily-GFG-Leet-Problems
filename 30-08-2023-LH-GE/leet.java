class Solution {
    public long minimumReplacement(int[] nums) {
        // Intuition :: rightmost element will never be changed
        // - from right to left iterate
        // at each step, if curr no > lb, modify lb
        //      3,7,6 -> here lb = 6 and curr no = 7
        //      7 can be broken to 1,6 OR 2,5 OR 3,4
        //      here we need to ensure that we pick 3,4..so that lb must be high, and cus of which on left side, u wont need to break elements or break in small size
        //      
        int n = nums.length;
        int lb = nums[n - 1];
        long ans = 0;

        for(int i = n - 2; i >= 0; i--) {
            if(nums[i] > lb) {
                // some operations
                int steps = (int)(Math.ceil((double)nums[i] / (double)lb)) - 1;
                ans += steps;

                lb = Math.min(lb, nums[i] / (steps + 1));   // this logic helps to get to '3,4' 
            } else  lb = nums[i];
        }
        return ans;
    }
}