class Solution {
    public int countSubarrays(int[] arr) {
        // code here
        Stack<Integer> st = new Stack<>();
        int n = arr.length, ans = 0;
        for (int i = n - 1; i >= 0; i--) {
            while(!st.isEmpty() && arr[st.peek()] >= arr[i])    st.pop();
            if(st.isEmpty()) {
                ans += (n - i - 1);
            } else {
                int len = st.peek() - i - 1;
                ans += len;
            }
            st.push(i);
        }
        return ans + n;
    }
}
