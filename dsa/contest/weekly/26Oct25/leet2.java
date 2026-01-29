class Solution {
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        for(int i = 0; i < n; i++)
            nums[i] = Math.abs(nums[i]);
        Arrays.sort(nums);
        int l = 0, r = nums.length - 1;
        long ans = 0;
        while(l <= r) {
            if(l == r)
                ans += nums[l] * nums[l];
            else {
                long smaller = nums[l] * nums[l], bigger = nums[r] * nums[r];
                ans += bigger - smaller;
            }
            l++;
            r--;
        }
        return ans;
    }
}

// -3 -2 -1 1 2 3
// 1 1 2 2 3 3
// 3 1, 3 1, 2 2

// 1 2 3
// 3 1 2



