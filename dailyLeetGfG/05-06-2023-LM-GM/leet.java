class Solution {
    public int longestSubarray(int[] nums) {
        int n = nums.length;
        int prev = 0;
        int maxOnes = nums[0];
        for(int i = 1; i < n; i++) {
            if(nums[i] != 0) {
                nums[i] += nums[i - 1];
                maxOnes = Math.max(maxOnes, nums[i]);
            }
        }
        if(maxOnes == nums.length)  // no 0s in array
            return maxOnes - 1;

        int prevSum = 0;    // holds value of sum of previous list of consecutive ones
        for(int i = 0; i < n; ) {
            if(i < n && nums[i] != 0) {
                while(i < n && nums[i] != 0) {
                    i++;
                }
                maxOnes = Math.max(maxOnes, prevSum + nums[i - 1]); 
                prevSum = nums[i - 1];
            } else {
                int k = 0;
                while(i < n && nums[i] == 0) {
                    i++;
                    k++;
                }
                if(k > 1)
                    prevSum = 0;    // as 2 or more zeroes fell between two subarrays of non-zero elems, resetting it
            }
        }
        return maxOnes;
    }
}