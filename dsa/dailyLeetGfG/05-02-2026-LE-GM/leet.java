class Solution {
    public int[] constructTransformedArray(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        for(int i = 0; i < n; i++) {
            int curr = nums[i];
            int step = Math.abs(curr) % n;
            if(curr > 0) {
                if (i + step < n)
                    ans[i] = nums[i + step];
                else {
                    step -= n - i;
                    ans[i] = nums[step];
                }
            } else {
                if (i - step >= 0)
                    ans[i] = nums[i - step];
                else {
                    step -= i;
                    ans[i] = nums[n - step];
                }
            }
        }
        return ans;
    }
}

// 0 1 2 3 , k=10

