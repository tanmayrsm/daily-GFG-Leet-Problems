
class Solution {
    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);
        // Binary search - for each differnce, count how many pairs there
        // eg - k = 7, and pairs with difference 1 => 6, 2 => 8, 3 => 14
        // then here, '2' is the answer

        int l = 0, r = nums[nums.length - 1], ans = Integer.MAX_VALUE;
        while (l < r) {
            int mid = (l + r) / 2;
            int count = countPairsWithDiffLessThanEqualTo(nums, mid);
            if (count >= k) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return r;

    }

    private static int countPairsWithDiffLessThanEqualTo(int[] nums, int diff) {
        int l = 0,  res = 0;
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] - nums[l] > diff)
                l++;
            res += i - l;
        }
        return res;
    }
}