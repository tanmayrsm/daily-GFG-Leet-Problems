class Solution {

    int countPairs(int arr[], int target) {
        // Complete the function
        int l = 0, r = arr.length - 1, ans = 0;
        while(l < r) {
            while(l < r && arr[l] + arr[r] < target)    l++;
            while(l < r && arr[l] + arr[r] > target)    r--;
            if (arr[l] + arr[r] == target) {
                if (arr[l] == arr[r]) {
                    int combo = r - l + 1;
                    ans += (combo * (combo - 1)) / 2;
                    return ans;
                }
                int oldL = arr[l], oldR = arr[r], ctL = 0, ctR = 0, L = l;
                while(l < r && oldL == arr[l]) {
                    ctL++;
                    l++;
                }
                while(L < r && oldR == arr[r]) {
                    ctR++;
                    r--;
                }
                ans += ctL * ctR;
            }
        }
        return ans;
    }
}