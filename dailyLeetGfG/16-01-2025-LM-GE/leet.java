class Solution {
    public int xorAllNums(int[] nums1, int[] nums2) {
        int n1 = nums1.length, n2 = nums2.length, ans = 0;
        if (n2 % 2 == 1) {
            for(int x : nums1)
                ans ^= x;
        }
        if (n1 % 2 == 1) {
            for (int x : nums2)
                ans ^= x;
        }
        return ans;
    }
}