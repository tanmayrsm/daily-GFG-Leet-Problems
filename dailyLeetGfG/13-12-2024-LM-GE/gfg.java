
class Solution {
    public int findMin(int[] arr) {
        // complete the function here
        // find peak at left
        int n = arr.length, l = 0, r = n - 1, minElem = arr[0];
        while (l <= r) {
            int mid = (l + r) / 2;
            // System.out.println(l + "::" + r + " :: " + mid);
            // left side balanced
            minElem = Math.min(minElem, arr[mid]);
            if(arr[r] >= arr[mid]) {
                minElem = Math.min(minElem, arr[l]);
                r = mid - 1;
            } else {
                minElem = Math.min(minElem, arr[r]);
                l = mid + 1;
            }
        }
        return minElem;
    }
}