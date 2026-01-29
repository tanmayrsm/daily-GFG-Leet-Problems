class Solution {
    public int longestSubarray(int[] nums) {
        int n = nums.length;
        if(n == 1 || n == 2)    return n;
        int ans = 2, curr = 2;
        for (int i = 2; i < n; i++) {
            if (arr[i] == (arr[i - 1] + arr[i - 2])) {
                if (curr == 0)  curr = 3;
                else curr++;
            }
            else {
                curr = 0;
            }
            ans = Math.max(ans, curr);
        }
        return ans;
    }
}