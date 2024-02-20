class Solution {
    public int missingNumber(int[] nums) {
        int n = nums.length;
        for(int i = 0; i < n; i++) {
            int no = nums[i];
            while(no < nums.length && no >= 0) {
                int temp = nums[no];
                nums[no] = -1;
                no = temp;
            }
        }
        for(int i = 0; i < n; i++)
            if(nums[i] >= 0)
                return i;
        
        return nums.length;
    }
}