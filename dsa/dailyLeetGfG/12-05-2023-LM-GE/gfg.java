
class Solution {
    public static int arrayOperations(int n, int[] arr) {
        // code here
        int ans = 0;
        int i = 0;
        while(i < n) {
            if(arr[i] == 0 && i + 1 < n && arr[i + 1] != 0) {   // if zero encountered, and next element is non-zero
            // increment counter, and go to next 0 element
                i++;
                while(i < n && arr[i] != 0) {
                    i++;
                }
                ans++;
            } else  i++;
        }
        if(arr[0] != 0) // as initial positive nos, didnt had 0 before them
            ans++;
        return ans;
    }
}