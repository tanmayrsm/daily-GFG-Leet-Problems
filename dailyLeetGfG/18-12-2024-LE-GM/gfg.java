class Solution {
    public static int findPages(int[] arr, int k) {
        // code here
        int n = arr.length, ans = -1;
        if (k > n)  return ans;
        int r = Arrays.stream(arr).sum(), l = 1;
        while(l <= r) {
            int mid = (l + r) / 2;
            if (isValid(arr, k, mid)) {
                ans = mid;
                r = mid - 1;
            } else l = mid + 1;
        }
        return ans;
        
    }
    private static boolean isValid(int[] arr, int k, int maxo) {
        int currSum = 0;
        for(int i = 0; i < arr.length; i++) {
            if (arr[i] > maxo)  return false;
            if (currSum + arr[i] > maxo) {
                k--;
                if (k == 0) return false;
                currSum = arr[i];
            } else currSum += arr[i];
        }
        return true;
    }
}