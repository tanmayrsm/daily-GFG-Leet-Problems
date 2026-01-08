class Solution {
    public int countSubarrays(int[] arr, int k) {
        // code here
        int n = arr.length, r = 0, l = 0, currOdds = 0, ans = 0;
        while (r < n) {
            while (r < n && currOdds < k) {
                if(arr[r] % 2 == 1) currOdds++;
                r++;
            }
            if(currOdds == k)  {
                int right = 0, left = 0;
                while (r < n && arr[r] % 2 == 0)    {
                    r++;
                    right++;
                }
                while (l < r && arr[l] % 2 == 0)    {
                    l++;
                    left++;
                }
                ans += (left + 1) * (right + 1);
                // System.out.println("ans :" + ans);
            }
            if(arr[l] %2 == 1) {
                l++;
                currOdds--;
            }
        }
        return  ans;
    }
}

0 0 1 0 1 0 1


0 0 1 0 1 (l -> 2) -> 3
        (r -> 1) -> 2 ~ 6



