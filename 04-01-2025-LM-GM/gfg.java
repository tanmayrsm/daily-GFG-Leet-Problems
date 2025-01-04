class Solution {
    public int countTriplets(int[] arr, int target) {
        // Code Here
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            // if (arr[i] < target)
                ans += getCt(arr, target - arr[i], i + 1);
        }
        return ans;
    }
    private int getCt(int[] arr, int tgt, int curr) {
        int l = curr, r = arr.length - 1, ans = 0;
        while(l < r) {
            if (arr[l] + arr[r] < tgt)
                while(l < r && arr[l] + arr[r] < tgt)   l++;
            else if (arr[l] + arr[r] > tgt)
                while(l < r && arr[l] + arr[r] > tgt)   r--;
                
            if (arr[l] + arr[r] == tgt) {
                if (arr[l] == arr[r]) {
                    int dist = r - l + 1;
                    return (dist * (dist - 1)) / 2;
                } else {
                    int ctL = 1, ctR = 1;
                    while(l + 1 < r && arr[l] == arr[l + 1]) {
                        l++;
                        ctL++;
                    }
                    while(r - 1 > l && arr[r] == arr[r - 1]) {
                        r--;
                        ctR++;
                    }
                    ans += ctL * ctR;
                }
                l++;
                r--;
            } 
        }
        return ans;
    }
}