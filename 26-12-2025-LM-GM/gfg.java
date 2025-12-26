class Solution {
    public int kthMissing(int[] arr, int k) {
        // code here
        int n = arr.length, lastMiss = 0, missCt = 0;
        if (arr[0] > k) return k;
        missCt = arr[0] - 1;
        for (int i = 1; i < n; i++) {
            int ct = arr[i] - arr[i - 1] - 1;
            if(ct == 0) continue;
            int total = missCt + ct;
            if(k <= total) {  // got ur no
                int start = arr[i - 1];
                return start + (k - missCt);
            }
            missCt += ct;
        }
        return arr[n - 1] + (k - missCt);
    }
}