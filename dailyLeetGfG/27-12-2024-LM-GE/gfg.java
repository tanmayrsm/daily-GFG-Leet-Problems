class Solution {

    int countPairs(int arr[], int target) {
        // Your code here
        Arrays.sort(arr);
        int l = 0, r = arr.length - 1, ans = 0;
        while(l < r) {
            if (r >= 0 && arr[l] + arr[r] > target)   r--;
            else if (l < r && arr[l] + arr[r] < target)  l++;
            else {
                int lefter = arr[l], righter = arr[r], L = 0, R = 0;
                if (arr[l] == arr[r]) {
                    int d = r - l;
                    // System.out.println("D ::" + d);
                    ans += d * (d + 1) / 2;
                    break;
                }
                    
                while(l < r && arr[l] == lefter) {
                    l++;
                    L++;
                }
                while(r >= 0 && arr[r] == righter) {
                    r--;
                    R++;
                }
                // System.out.println(L + "::" + R);
                ans += L * R;
            }
        }
        return ans;
    }
}