
class Solution {
    public int kthMissing(int[] arr, int k) {
        // code here
        int last = arr[0], diff = arr[0], currK = arr[0] - 1, n = arr.length;
        if (k < arr[0]) return k;
        if (currK >= k)  return k;
        
        for(int i = 0; i < arr.length - 1; i++) {
              if (arr[i + 1] - arr[i] - 1 + currK >= k) {
                  return arr[i] + (k - currK);
              }
              currK += arr[i + 1] - arr[i] - 1;
        }
        return arr[n - 1] + (k - currK);
        
    }
}