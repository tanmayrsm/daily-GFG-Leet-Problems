class Solution {
  public int[][] divideArray(int[] nums, int K) {
    int n = nums.length, k = 0;
    int[][] ans = new int[0][0];
    if (n % 3 != 0) return ans;
    int[][] expectedAns = new int[n / 3][3];
    Arrays.sort(nums);

    for (int i = 0; i < n; i += 3) {
      if (nums[i + 2] - nums[i] > K)  return ans;
      int col = 0;
      for (int j = i; j <= i + 2; j++)
        expectedAns[k][col++] = nums[j];
      k++;
    }
    return expectedAns;
  }
}