class Solution {
    public int majorityElement(int[] nums) {
        
        // MOORE VOTING ALOG
        int majIndex = 0, ct = 1, n = nums.length;
        for(int i = 1; i < n; i++) {
            if(nums[majIndex] == nums[i])   ct++;
            else ct--;
            if(ct == 0) {
                majIndex = i;
                ct = 1;
            }
        }
        return nums[majIndex];
    }
}