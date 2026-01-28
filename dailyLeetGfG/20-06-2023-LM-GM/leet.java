class Solution {
    public int[] getAverages(int[] nums, int k) {
        if(k == 0)
            return nums;
        int n = nums.length;
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        
        long sum = 0;
        for(int i = 0; i < 2*k + 1 && i < n; i++)   // add first 2k + 1 instances and store in 'sum'
            sum += nums[i];

        for(int i = k; i < n - k; i++) {   // from i = k to n - k
            if(i - k - 1 >= 0)
                sum -= nums[i - k - 1];     // if I have moved to second, third... interval, remove nums[last number in range]
            ans[i] = (int)(sum / (2*k + 1));
            if(i + k + 1 < n)               // add nums[ next number in range ]
                sum += nums[i + k + 1];
        }
        return ans;
    }
}