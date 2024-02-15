class Solution {
    public long largestPerimeter(int[] nums) {
        Arrays.sort(nums);
        long sum = 0;
        int n = nums.length;
        for(int x : nums)   sum += x;
        for(int i = n - 1; i >= 0; i--) {
            long rem = sum - nums[i];
            if(rem > nums[i])   return sum;
            sum -= nums[i];
        }
        return -1;
    }
}