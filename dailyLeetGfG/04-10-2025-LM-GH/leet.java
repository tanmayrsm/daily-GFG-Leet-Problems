class Solution {
    public int maxArea(int[] height) {
        int n = height.length, l = 0, r = n - 1, ans = 0;
        while (l < r) {
            int b = r - l, ht = Math.min(height[l], height[r]);
            ans = Math.max(ans, ht * b);
            if (height[l] <= height[r]) l++;
            else r--;
        }
        return ans;
    }
}


