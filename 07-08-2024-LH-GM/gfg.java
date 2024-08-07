
// User function Template for Java

class Solution {
    public long kthElement(int k, int arr1[], int arr2[]) {
        // code here
        // u can easily do it in O(k) time
        // however, there's another approach to solve as per given time complexity
        // a similar variant is - Median of two sorted arrays
        
        // I always want first param array to be smaller
        int n1 = arr1.length, n2 = arr2.length;
        if (n1 > n2)  return kthElement(k, arr2, arr1);
        
        // size of final array will be O(n + m)

        int l = Math.max(k - n2, 0), r = Math.min(k, n1);
        int left = k;
        while (l <= r) {
            int mid1 = (l + r) / 2;
            int mid2 = left - mid1;
            int l1 = Integer.MIN_VALUE, l2 = l1;
            int r1 = Integer.MAX_VALUE, r2 = r1;
            if (mid1 < n1)  r1 = arr1[mid1];
            if (mid2 < n2)  r2 = arr2[mid2];
            if (mid1 >= 1)  l1 = arr1[mid1 - 1];
            if (mid2 >= 1)  l2 = arr2[mid2 - 1];
            
            if (l1 <= r2 && l2 <= r1)
                return Math.max(l1, l2);
            else if (l1 > r2)   r = mid1 - 1;
            else l = mid1 + 1;
        }
        return 0;
    }
}