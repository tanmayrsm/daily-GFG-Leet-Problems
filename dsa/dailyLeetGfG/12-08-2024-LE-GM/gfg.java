
class Solution {
    public int sumOfMiddleElements(int[] arr1, int[] arr2) {
        // code here
        int n = arr1.length, m = arr2.length;
        if (n > m)
            return sumOfMiddleElements(arr2, arr1);
        int l = 0, r = n;
        int left = (n + m + 1) / 2;
        while (l <= r) {
            int mid1 = (l + r) / 2;
            int mid2 = left - mid1;
            int l1 = Integer.MIN_VALUE, l2 = l1;
            int r1  =Integer.MAX_VALUE, r2 = r1;
            if (mid1 < n) r1 = arr1[mid1];
            if (mid2 < m) r2 = arr2[mid2];
            if (mid1 >= 1) l1 = arr1[mid1 - 1]; // just elem before mid1
            if (mid2 >= 2)  l2 = arr2[mid2 - 1];    // just elem before mid2

            if (l1 <= r2 && l2 <= r1) {
                // symmetry found
                // System.out.println(l1 + "::" + l2 + "::" + r1 + ":::" + r2);
                if (n + m % 2 == 1) return Math.max(l1, l2);
                else return (Math.max(l1, l2) + Math.min(r1, r2));
            }
            else if (l1 > r2)
                r = mid1 - 1;
            else l = mid1 + 1;
            
        }
        return 0; // This case will never occur for valid input
    }
}