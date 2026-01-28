
class Solution {
    int findMaxSum(int a[], int n) {
        // code here
        if(n == 1)  return a[0];
        if(n == 2)  return Math.max(a[0], a[1]);
        int maxo = a[0], ans = a[0];
        for(int i = 2; i < n; i++) {
            a[i] += Math.max(a[i - 2], maxo);
            maxo = Math.max(maxo, a[i - 1]);
            ans = Math.max(ans, a[i]);
        }
        return ans;
    }
}