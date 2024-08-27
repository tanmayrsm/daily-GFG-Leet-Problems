import java.util.Stack;

class Solution {
    public int findMaxDiff(int[] arr) {
        // code here
        Stack<Integer> st = new Stack<>();
        int n = arr.length, ans = 0;
        int[] leftSideSmall = new int[n];
        // int[] rightSideSmall = new int[n];
        st.push(arr[0]);
        for (int i = 1; i < n; i++) {
            while (!st.isEmpty() && st.peek() >= arr[i]) 
                st.pop();
            if (!st.isEmpty())
                leftSideSmall[i] = st.peek();
            st.push(arr[i]);
        }

        st.clear();
        st.push(arr[n - 1]);
        ans = leftSideSmall[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            int currRightSideSmall = 0;
            while (!st.isEmpty() && st.peek() >= arr[i]) 
                st.pop();
            if (!st.isEmpty())
                currRightSideSmall = st.peek();
            st.push(arr[i]);
            ans = Math.max(ans, Math.abs(leftSideSmall[i] - currRightSideSmall));
        }

        return ans;

    }
}


// Given an integer array arr of integers, the task is to find the maximum absolute difference between the nearest left smaller element and the nearest right smaller element of every element in array arr. If for any component of the arr, the nearest smaller element doesn't exist then consider it as 0.

// Examples :

// Input: arr = [2, 1, 8]
// Output: 1
// Explanation: left smaller array ls = [0, 0, 1], right smaller array rs = [1, 0, 0]. Maximum Diff of abs(ls[i] - rs[i]) = 1
// Input: arr = [2, 4, 8, 7, 7, 9, 3]
// Output: 4
// Explanation: left smaller array ls = [0, 2, 4, 4, 4, 7, 2], right smaller rs = [0, 3, 7, 3, 3, 3, 0]. Maximum Diff of abs(ls[i] - rs[i]) = abs(7 - 3) = 4
// Expected Time Complexity: O(n)
// Expected Space Complexity: O(n)


// [2, 4, 8, 7, 7, 9, 3]
