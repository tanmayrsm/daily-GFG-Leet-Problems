class Solution {
    public boolean isMonotonic(int[] nums) {
        int n = nums.length;
        if(n == 1)    return true;
        Boolean isMonotoneInc = false;
        int k = 1;
        while(k < n && nums[k] == nums[k - 1]) {
            k++;
        }
        if(k == n)  return true;
        isMonotoneInc = nums[k] >= nums[k - 1];
        for(int i = k + 1; i < n; i++) {
            if(isMonotoneInc && nums[i] < nums[i - 1])  return false;
            if(!isMonotoneInc && nums[i] > nums[i - 1])  return false;
        }
        return true;
    }
}