class Solution {
    int bs(int[] nums, int l, int r, int target) {
        // NOTE -  either of left or right side will always be sorted
        int mid = (l + r) / 2;
        if(nums[mid] == target) {
            return mid;
        } 
        
        // System.out.println("nos ::" + l + " :: " + mid + "::" + r + " :: " + target + " :: " + nums[mid]);
        if(l >= r)   return -1;
        if(nums[l] < nums[mid]) {
            // left side is sorted
            if(nums[l] <= target && nums[mid] >= target) {
                // element on left side
                return bs(nums, l, mid, target);
            } else {
                // check on right unsorted side
                return bs(nums, mid + 1, r, target);
            }
        } else {
            // right side is sorted
            if(nums[mid + 1] <= target && target <= nums[r]){
                // element on right sorted side
                return bs(nums, mid + 1, r, target);
            }
            else {
                // check on left unsorted side
                return bs(nums, l, mid, target);
            }
        }
    }
    public int search(int[] nums, int target) {
        return bs(nums, 0, nums.length - 1, target);
    }
}