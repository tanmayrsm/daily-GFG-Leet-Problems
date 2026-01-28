class Solution {
    public static String maxSubseq(String s, int k) {
        // code here
        Stack<Character> st = new Stack<>();
        StringBuilder sb = new StringBuilder();

        int n = s.length();
        for (int i = 0; i < n; i++) {
            char curr = s.charAt(i);
            if (!st.isEmpty() && k > 0 && st.peek() < curr) {
                while (k > 0 && !st.isEmpty() && st.peek() < curr) {
                    k--;
                    st.pop();
                }
            }
            st.push(curr);
        }

        while (!st.isEmpty()) {
            char poped = st.pop();
            if (k > 0) {
                k--;
            } else sb.append(poped + "");
        }

        sb.reverse();
        return sb.toString();
    }
}