
class Solution {
    int transitionPoint(int arr[], int n) {
        // code here
        int l = 0, r = n - 1, ans = -1;
        while(l <= r) {
            int mid = r - (r - l) / 2;
            if(arr[mid] == 1) {
                ans = mid;
                r = mid - 1;
            } else  l = mid + 1;
        }
        return ans;
    }
}