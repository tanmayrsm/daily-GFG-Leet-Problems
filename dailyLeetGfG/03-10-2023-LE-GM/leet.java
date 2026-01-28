class Solution {
    public int numIdenticalPairs(int[] nums) {
        Arrays.sort(nums);
        int ans = 0;
        int n = nums.length, l = 0, r = l + 1;
        for(int i = 1; i < n + 1; i++) {
            if(i == n || nums[i] != nums[i - 1]) {  // i == n -> for checking last elem, e.g 1,1,1,1
                int currLen = i - l - 1;
                ans += currLen * (currLen + 1) / 2;
                l = i;
            }
        }
        return ans;
    }
}