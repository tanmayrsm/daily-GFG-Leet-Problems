class Solution {
    public int[] sortArrayByParity(int[] nums) {
        int l = 0, n = nums.length, r = n - 1;
        while(l < r && l < n && r >= 0) {
            while(l < n && nums[l] % 2 == 0) {   // go at first odd from left
                l++;
            }
            while(r >= 0 && nums[r] % 2 != 0) { // go at last even from right
                r--;
            }
            if(l < r && l < n && r >= 0) {
                int temp = nums[l];
                nums[l] = nums[r];
                nums[r] = temp;
            }
        }
        return nums;
    }
}