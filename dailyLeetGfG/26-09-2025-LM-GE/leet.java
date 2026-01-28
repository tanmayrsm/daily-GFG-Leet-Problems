class Solution {
    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length, ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int idxGreater = bs(nums, n, nums[i] + nums[j], j + 1);
                if (idxGreater != -1) {
                    ans += idxGreater - j;
                }
            }
        }
        return ans;
    }
    private int bs(int[] nums, int n, int no, int from) {
        int l = from, r = n - 1, mid = (l + r) / 2, idx = -1;
        while (l <= r) {
            mid = (l + r) / 2;
            if (nums[mid] >= no) {
                r = mid - 1;
            } else {
                idx = mid;
                l = mid + 1;
            }
        }
        return idx;
    }
}

// 2 2 3 4


