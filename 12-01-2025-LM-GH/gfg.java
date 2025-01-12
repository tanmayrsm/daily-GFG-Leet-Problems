class Solution {
    public int maxWater(int arr[]) {
        // code here
        int n = arr.length;
        int ans = 0;
        int currTotal = 0;
        
        int currBoss = arr[0];
        
        for(int i = 1; i < n; i++) {
            if(arr[i] >= currBoss) {
                ans += currTotal;
                currTotal = 0;
                currBoss = arr[i];
            } else {
                currTotal += currBoss - arr[i];
            }
        }
        
        currTotal = 0;
        currBoss = arr[n - 1];
        
        for(int i = n - 2; i >= 0; i--) {
            if(arr[i] > currBoss) {
                ans += currTotal;
                currTotal = 0;
                currBoss = arr[i];
            } else {
                currTotal += currBoss - arr[i];
            }
        }
        
        return ans;
    }
}