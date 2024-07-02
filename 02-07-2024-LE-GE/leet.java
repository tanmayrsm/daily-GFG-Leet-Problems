
class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int n = nums1.length, m = nums2.length, k = 0;
        List<Integer> list = new ArrayList<>();
        while (n > 0 && m > 0) {
            if (nums1[n - 1] == nums2[m - 1]) {
                n--; m--;
                list.add(nums1[n]);
            } else if (nums1[n - 1] > nums2[m - 1]) {
                n--;
            } else {
                m--;
            }
        }

        int[] ans = new int[list.size()];
        for(Integer l : list)
            ans[k++] = l;
        return ans;
    }
}