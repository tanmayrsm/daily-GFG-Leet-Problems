class Solution {
    public int maxDistance(int[] nums1, int[] nums2) {
        int n = nums1.length, ans = 0;
        for (int i = 0; i < n; i++) {
            int idx = binSearch(nums2, nums1[i]);
            if(idx != -1 && idx >= i && nums2[idx] >= nums1[i]) {
                ans = Math.max(ans, Math.abs(i - idx));
            }
        }
        return ans;
    }
    private int binSearch(int[] nums, int no) {
        // find rightmost index in nums where x >= no
        int n = nums.length, l = 0, r = n - 1, ans = -1;
        while(l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] < no) {
                r = mid - 1;
            } else {
                ans = mid;
                l = mid + 1;
            }
        }
        return ans;
    }
}