class Solution {
  public int partitionArray(int[] nums, int k) {
    int n = nums.length, l = 0, r = 0, ans = 0;
    Arrays.sort(nums);
    for (int i = 0; i < n; i++) {
      if (r >= n) break;
      if (nums[r] - nums[l] > k) {
        l = i; r = i + 1;
        ans++;
      } else  {
        r++;
        // if (r >= n) return ans;
      }
    }
    return ans + 1;
  }
}
// 2,2,4,5 and k = 0
// - - 1 2

// 1 2 3, k = 1
// - - 1