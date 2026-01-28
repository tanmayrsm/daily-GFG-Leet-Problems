class Solution {
    public void sortColors(int[] nums) {
        // approach -
        // sort 1s
        // from L -> R
        // find first non-0
        //  go on right till u get a 0
        //  once 0, swap both
        //  move non-0 ahead

        int n = nums.length;
        // sort 0s
        int non = 0, fwd = 0;
        while(fwd < n && non < n) {
            while (non < n && nums[non] == 0) {
                non++;
            }
            fwd = non + 1;
            while (fwd < n && nums[fwd] != 0) {
                fwd++;
            }
            if(fwd < n && non < n && nums[fwd] == 0 && nums[non] != 0) {
                int temp = nums[fwd];
                nums[fwd] = nums[non];
                nums[non] = temp;
            }
            fwd++;
            non++;
        }

        // sort 2s
        non = n - 1; fwd = n - 1;

        while(fwd >= 0 && non >= 0) {
            while (non >= 0 && nums[non] == 2) {
                non--;
            }
            fwd = non - 1;
            while (fwd >= 0 && nums[fwd] != 2) {
                fwd--;
            }
            if(non > fwd && fwd >= 0 && non >= 0 && nums[fwd] == 2 && nums[non] != 2) {
                int temp = nums[fwd];
                nums[fwd] = nums[non];
                nums[non] = temp;
            }
            fwd--;
            non--;
        }
    }
}