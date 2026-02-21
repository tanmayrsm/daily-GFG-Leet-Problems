class Solution {
    public int minTime(int[] arr, int k) {
        // code here
        int r = Arrays.stream(arr).sum();
        int l = 0;
        int ans = r;
        while(l <= r) {
            int mid = (l + r) / 2;
            if(isPossible(arr, k, mid)) {
                ans = mid;
                r = mid - 1;
            } else l = mid + 1;
        }
        return ans;
    }
    private boolean isPossible(int[] arr, int k, int target) {
        int currSum = 0, n = arr.length;
        for (int i = 0; i < n; i++) {
            if(arr[i] > target) return false;
            if(arr[i] + currSum > target) {
                currSum = arr[i];
                k--;
            } else currSum += arr[i];
            if(k == 0) return false;
        }
        return true;
    }
}
