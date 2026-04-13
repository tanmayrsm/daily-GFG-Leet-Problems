class Solution {
    public int getMinDistance(int[] nums, int target, int start) {
        int n = nums.length, l = start, r = start;
        int ans = n;
        while(l >= 0 && r < n) {
            if(nums[l] == target)   return Math.abs(start - l);
            if(nums[r] == target)   return Math.abs(start - r);
            l--;
            r++;
        }
        while(l >= 0) {
            if(nums[l] == target)   return Math.abs(start - l);
            l--;
        }
        while(r < n) {
            if(nums[r] == target)   return Math.abs(start - r);
            r++;
        }
        return -1;
    }
}