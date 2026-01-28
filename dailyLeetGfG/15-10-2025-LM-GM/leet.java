class Solution {
    public int maxIncreasingSubarrays(List<Integer> nums) {
        int n = nums.size(), l = 1, r = n / 2, ans = 0;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (existsMaxK(nums, mid, n)) {
                ans = mid;
                l = mid + 1;
            } else r = mid - 1;
        }
        return ans;
    }
    private boolean existsMaxK(List<Integer> nums, int k, int n) {
        int knew = k - 1;
        if (knew == 0) return true;
        for (int j = k + 1; j < n; j++) {
            if (nums.get(j) > nums.get(j - 1) && nums.get(j - k) > nums.get(j - k - 1)) {
                knew--;
            } else {
                knew = k - 1;
            }
            if (knew == 0) return true;
        }
        return false;
    }
}