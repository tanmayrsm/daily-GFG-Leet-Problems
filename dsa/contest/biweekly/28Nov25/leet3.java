class Solution {
    public long minOperations(int[] nums1, int[] nums2) {
        int n = nums1.length;
        long ans = 0;
        int extra = nums2[n], minOps = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int min = Math.min(nums1[i], nums2[i]), max = Math.max(nums1[i], nums2[i]);
            long ops = max - min;
            ans += ops;
            if(extra >= min && extra <= max) {
                minOps = 1;
            } else if (extra < min) {
                minOps = Math.min(minOps, 1 + min - extra);
            } else if (extra > max) {
                minOps = Math.min(minOps, 1 + extra - max);
            }
        }
        // System.out.println(ans + "::" + minOps);
        return ans + minOps;
    }
}