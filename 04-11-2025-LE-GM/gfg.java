class Solution {
    int minCost(int[] height) {
        // code here
        int n = height.length;
        if (n == 1) return 0;

        int a1 = 0, a2 = Math.abs(height[1] - height[0]), a3 = 0;
        if (n == 2) return a2;

        for (int i = 2; i < n; i++) {
            int oneStep = Math.abs(height[i] - height[i - 1]);
            int twoStep = Math.abs(height[i] - height[i - 2]);
            // ans[i] = Math.min(ans[i - 1] + oneStep, ans[i - 2] + twoStep);
            a3 = Math.min(a2 + oneStep, a1 + twoStep);
            int temp = a2;
            a2 = a3;
            a1 = temp;
            // System.out.println(ans[i]);
        }
        return a3;
    }
}

// 20 30 40 20
// 0  10 20 (40,20)
