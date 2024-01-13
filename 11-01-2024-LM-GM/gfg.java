
class Solution {

    public String removeKdigits(String S, int K) {
        int n = S.length();
        int[] v = new int[10];
        Stack<Character> st = new Stack<>();

        for (int i = 0; i < n; i++) {
            while (!st.isEmpty() && S.charAt(i) < st.peek() && K > 0) {
                st.pop();
                K--;
            }
            if (st.isEmpty() && S.charAt(i) == '0') {
                continue;
            }
            st.push(S.charAt(i));
        }

        while (!st.isEmpty() && K > 0) {
            st.pop();
            K--;
        }

        if (st.isEmpty()) {
            return "0";
        }

        StringBuilder ans = new StringBuilder();
        while (!st.isEmpty()) {
            ans.append(st.pop());
        }

        return ans.reverse().toString();
    }
}