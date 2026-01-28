class Solution {
    public int[] finalPrices(int[] prices) {
        int n = prices.length;
        int[] ans = new int[n];
        ans[n - 1] = prices[n - 1];
        Stack<Integer> st = new Stack<>();
        st.add(n - 1);

        for (int i = n - 2; i >= 0; i--) {
            while(!st.isEmpty() && prices[st.peek()] > prices[i])
                st.pop();
            if (!st.isEmpty())
                ans[i] = prices[i] - prices[st.peek()];
            else ans[i] = prices[i];
            st.push(i);
        }

        return ans;

    }
}