class Solution {
    public int visibleBuildings(int arr[]) {
        // code here
        int n = arr.length, currMax = arr[0], ans = 1;
        for(int i = 1; i < n; i++) {
            if(arr[i] >= currMax) {
                currMax = arr[i];
                ans++;
            }
        }
        return ans;
    }
}