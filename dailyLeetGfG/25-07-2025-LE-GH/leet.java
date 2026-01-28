class Solution {
  public int maxSum(int[] nums) {
    Arrays.sort(nums);
    int n = nums.length, curr = 0;
    if (nums[n - 1] <= 0)   return nums[n - 1];
    for (int i = 0; i < n; i++) {
      if (nums[i] > 0 && (i == 0 || nums[i] != nums[i - 1]))
        curr += nums[i];
    }
    return curr;
  }
}