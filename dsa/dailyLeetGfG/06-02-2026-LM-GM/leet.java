class Solution {
    public int minRemoval(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length, ans = n - 1;
        for (int i = 0; i < n; i++) {
            int ceiler = (int)(Math.ceil((double)nums[i] / (double)k));
            int currAsMax = bsLeftmost(nums, 0, i - 1, ceiler);
            int currAsMin = bsRightmost(nums, i + 1, n - 1, nums[i] * k);

            if (currAsMax > -1) {
                ans = Math.min(ans, n - 1 - i + currAsMax);
            }
            if(currAsMin > -1) {
                ans = Math.min(ans, i + n - 1 - currAsMin);
            }
        }
        return ans;
    }

    // target -> 8*2, arr = ....4 7 16 16 18 20 ..must return n-2
    private int bsRightmost(int[] nums, int l, int r, int target) {
        int ans = -1;
        while(l <= r) {
            int mid = (l + r) / 2;
            if(nums[mid] <= target) {
                ans = mid;
                l = mid + 1;
            } else r = mid - 1;
        }
        return ans;
    }

    // target -> 9, search 5 ka leftmost position, arr = ....4 5 5 7 7 16 16 18 20 ..must return 1
    private int bsLeftmost(int[] nums, int l, int r, int target) {
        int ans = -1;
        while(l <= r) {
            int mid = (l + r) / 2;
            if(nums[mid] >= target) {
                ans = mid;
                r = mid - 1;
            } else l = mid + 1;
        }
        return ans;
    }

}