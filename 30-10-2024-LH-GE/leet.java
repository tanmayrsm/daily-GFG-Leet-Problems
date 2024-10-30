class Solution {
    public int minimumMountainRemovals(int[] nums) {
        int n = nums.length, ans = Integer.MIN_VALUE;
        int[] leftLongestDescent = new int[n];
        int[] rightLongestDescent = new int[n];
        
        for(int i = 1; i < n; i++) {
            for(int j = i - 1; j >= 0; j--) {
                if (nums[j] < nums[i])
                    leftLongestDescent[i] = Math.max(leftLongestDescent[i], 1 + leftLongestDescent[j]);
            }
        }
        for(int i = n - 2; i >= 0; i--) {
            for(int j = i + 1; j < n; j++) {
                if (nums[j] < nums[i])
                    rightLongestDescent[i] = Math.max(rightLongestDescent[i], 1 + rightLongestDescent[j]);
            }
        }
        for(int i = 1; i < n - 1; i++) {
            // System.out.println(leftLongestDescent[i] + "::" + rightLongestDescent[i]);
            if(leftLongestDescent[i] > 0 && rightLongestDescent[i] > 0)
                ans = Math.max(ans, leftLongestDescent[i] + rightLongestDescent[i] + 1);
        }
        if(ans == Integer.MIN_VALUE)    return n;
        return n - ans;
        
    }
}