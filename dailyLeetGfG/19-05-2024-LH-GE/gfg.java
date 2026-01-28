
class Solution {
    public static int findClosest(int n, int k, int[] arr) {
        // code here
        int l = 0, r = n - 1, diff = Integer.MAX_VALUE, val = -1;
        while(l <= r) {
            // System.out.println(l + "::" + r);
            int mid = (l + r) / 2;
            if(arr[mid] == k)   return k;
            int currDiff = Math.abs(arr[mid] - k); 
            if(currDiff <= diff) {
                if(diff == currDiff) {
                    if(val < arr[mid])
                        val = arr[mid];
                } else val = arr[mid];
                    
                diff = currDiff;
            }
            if(arr[mid] - k > 0)
                r = mid - 1;
            else l = mid + 1;
        }
        return val;
    }
}
