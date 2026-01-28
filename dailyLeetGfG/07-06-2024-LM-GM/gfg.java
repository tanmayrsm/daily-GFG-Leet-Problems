
class Solution {
    // Function to find the maximum occurred integer in all ranges.
    public static int maxOccured(int n, int l[], int r[], int maxx) {
        int[] arr = new int[maxx + 2]; 
        int ans = 0;
        for(int i = 0; i < n; i++) {
            arr[l[i]]++;
            arr[r[i] + 1]--;
        }
        // make prefix array to get count of index 'i' in each range
        for(int i = 1; i < arr.length; i++)
            arr[i] += arr[i - 1];
        
        for(int i = 0; i < arr.length; i++)
            if(arr[i] > arr[ans])    ans = i;
        
        return ans;
        
    }
}