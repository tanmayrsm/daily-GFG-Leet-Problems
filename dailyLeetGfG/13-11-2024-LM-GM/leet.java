class Solution {
    public long findLowerElements(int nums[],int target){
        int n = nums.length;
        int l = 0;
        int r = n-1;
        long pairs = 0;

        while(l < r){
            if(nums[l] + nums[r] <= target){
                pairs += (r-l);
                l++;
            }
            else r--;
        }

        return pairs;
    }
    public long countFairPairs(int[] nums, int lower, int upper) {
        Arrays.sort(nums);
        long up = findLowerElements(nums,upper);
        long low = findLowerElements(nums,lower-1);
        return up - low;
    }
}