class Solution {
    public int[] dailyTemperatures(int[] temp) {
        Stack<Integer> st = new Stack<>();
        int n = temp.length;
        int[] ans = new int[n];
        st.push(n - 1);

        for(int i = n - 2; i >= 0; i--) {
            while(!st.isEmpty() && temp[st.peek()] <= temp[i])
                st.pop();
            if(!st.isEmpty())
                ans[i] = st.peek() - i;
            st.push(i);
        }

        return ans;
    }
}