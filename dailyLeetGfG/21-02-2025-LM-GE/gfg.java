class Solution {
    static boolean isBalanced(String x) {
        // code here
        // add your code here
        Stack<Character> st = new Stack<>();
        int n = x.length();
        for (int i = 0; i < n; i++) {
            char curr = x.charAt(i);
            if (curr == '{' || curr == '[' || curr == '(')
                st.push(curr);
            else if (!st.isEmpty()) {
                    if (curr == ']' && st.peek() == '[')
                        st.pop();
                    else if (curr == '}' && st.peek() == '{')
                        st.pop();
                    else if (curr == ')' && st.peek() == '(')
                        st.pop();
                    else return false;
            } else return false;
        }
        return st.isEmpty();
    }
}