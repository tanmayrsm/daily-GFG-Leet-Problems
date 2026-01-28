import java.util.Stack;

class Solution {
    public int maxWidthRamp(int[] nums) {
        int n = nums.length;
        int ans = 0;
        Stack<Integer> stack = new Stack<>();

        // First pass: Push indices of potential left boundaries into the stack
        // We only push indices where the value is smaller than the previous stack top
        for (int i = 0; i < n; i++) {
            if (stack.isEmpty() || nums[stack.peek()] > nums[i]) {
                stack.push(i);
            }
        }

        // Second pass: Iterate from right to left to find the maximum width ramp
        for (int i = n - 1; i > 0; i--) {
            // While the stack is not empty and the current element is greater than or equal to the stack top
            while (!stack.isEmpty() && nums[stack.peek()] <= nums[i]) {
                // Calculate the width of the ramp and update the maximum
                // Remove the top element as we've found a valid ramp for it
                ans = Math.max(ans, i - stack.pop());
            }
        }

        // Return the maximum width ramp found
        return ans;
    }
}