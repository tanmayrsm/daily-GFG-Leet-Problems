class Solution {
    public int[] smallestSubarrays(int[] nums) {
        int[] last = new int[32];
        for (int l = nums.length - 1; l >= 0; l--){
            int r = l;
            for (int b = 0; b < 32; b++){
                if ((nums[l] & (1 << b)) != 0)
                    last[b] = l;
                else
                    r = Math.max(r, last[b]);
            }
            nums[l] = r - l + 1;
        }
        return nums;
    }
}