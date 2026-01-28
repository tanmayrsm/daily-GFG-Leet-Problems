class Solution {
    public int[] rearrangeArray(int[] nums) {
        int posiNo = 0, negaNo = 1, n = nums.length;
        int[] ans = new int[n];
        for(int i = 0; i < n; i++) {
            if(nums[i] > 0) {
                ans[posiNo] = nums[i];
                posiNo+=2;
            } else {
                ans[negaNo] = nums[i];
                negaNo+=2;
            }
        }
        return ans;
    }
}