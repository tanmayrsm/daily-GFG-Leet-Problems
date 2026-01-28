class Solution {
    public int getCommon(int[] nums1, int[] nums2) {
        int n = 0, m = 0;
        while(n < nums1.length && m < nums2.length) {
            if(nums1[n] == nums2[m])
                return nums1[n];
            if(nums1[n] < nums2[m])
                n++;
            else m++;
        }
        return -1;
    }
}