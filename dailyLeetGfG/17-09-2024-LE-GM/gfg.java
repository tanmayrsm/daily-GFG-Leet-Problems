
// User function Template for Java
class Solution {
    int getMinDiff(int[] arr, int k) {
        // code here
        int n = arr.length;
        if (n == 1) {
            return 0;
        }
        Arrays.sort(arr);
        int ans = arr[n - 1] - arr[0];
        for (int i = 1; i < n; i++) {
            if (arr[i] - k < 0) {
                continue;
            }
            int maxi = Math.max(arr[i - 1] + k, arr[n - 1] - k);
            int mini = Math.min(arr[0] + k, arr[i] - k);
            ans = Math.min(ans, maxi - mini);
        }

        return ans;
    }
}


// {3,  9, 12, 16, 20}
// sort above
// if k > max, return arr[last] - arr[0]
// {22, 28, 31, 35, 1}



//  {3, 9, 12, 16, 20}
//  k = 3

//  
//
