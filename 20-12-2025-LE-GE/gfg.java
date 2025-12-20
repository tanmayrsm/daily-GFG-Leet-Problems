class Solution {
    public int searchInsertK(int arr[], int k) {
        // code here
        int n = arr.length, l = 0, r = n - 1, ans = -1;
        while(l <= r) {
            int mid = (l + r) / 2;
            if (arr[mid] >= k) {
                ans = mid;
                r = mid - 1;
            } else l = mid + 1;
        }
        return (ans == -1) ? n : ans;
    }
    
};