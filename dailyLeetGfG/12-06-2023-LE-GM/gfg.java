
class Solution{
    public int cutRod(int price[], int n) {
        //code here
        int[] ans = new int[n];
        ans[0] = price[0];
        int maxi = ans[0];
        for(int i = 1; i < n; i++) {
            int l = 0;
            int r = i - 1;
            int maxo = price[i];
            while(l <= r) {
                maxo = Math.max(maxo, ans[l] + ans[r]);
                l++;
                r--;
            }
            ans[i] = maxo;
            maxi = Math.max(ans[i], maxi);
        }
        return maxi;
    }
}