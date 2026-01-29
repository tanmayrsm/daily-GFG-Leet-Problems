class Solution {
    public int trap(int[] arr) {
        int n = arr.length;
        int[] pref = new int[n];
        int[] suff = new int[n];
        int ans = 0;
        int currTotal = 0;
        
        pref[0] = 0;
        int currBoss = arr[0];
        
        for(int i = 1; i < n; i++) {
            if(arr[i] >= currBoss) {
                pref[i] = 0;
                ans += currTotal;
                currTotal = 0;
                currBoss = arr[i];
            } else {
                pref[i] = currBoss - arr[i];
                currTotal += pref[i];
            }
        }
        
        currTotal = 0;
        suff[n - 1] = 0;
        currBoss = arr[n - 1];
        
        for(int i = n - 2; i >= 0; i--) {
            if(arr[i] > currBoss) {
                suff[i] = 0;
                ans += currTotal;
                currTotal = 0;
                currBoss = arr[i];
            } else {
                suff[i] = currBoss - arr[i];
                currTotal += suff[i];
            }
        }
        
        return ans;
    }
}