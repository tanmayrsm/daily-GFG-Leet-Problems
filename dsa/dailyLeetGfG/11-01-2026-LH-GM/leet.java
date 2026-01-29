class Solution {
    private int largestRectangleArea(int[] heights) {
        Stack<int[]> st = new Stack<>();
        int maxArea = 0;
        int n = heights.length;

        for (int i = 0; i < n; i++) {
            int start = i;
            while (!st.isEmpty() && st.peek()[1] > heights[i]) {
                int[] top = st.pop();
                maxArea = Math.max(maxArea, top[1] * (i - top[0]));
                start = top[0];
            }
            st.push(new int[]{start, heights[i]});
        }

        while (!st.isEmpty()) {
            int[] top = st.pop();
            maxArea = Math.max(maxArea, top[1] * (n - top[0]));
        }

        return maxArea;
    }

    public int maximalRectangle(char[][] matrix) {
        int row = matrix.length, col = matrix[0].length;
        int[] height = new int[col];
        int ans = 0;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == '1')
                    height[j]++;
                else
                    height[j] = 0;
            }
            ans = Math.max(ans, largestRectangleArea(height));
        }
        return ans;
    }
}