import java.util.Stack;

class Solution {
    public int minSwaps(String s) {
        int n = s.length();
        Stack<Character> st = new Stack<>();
        for(int i = 0; i < n; i++) {
            char curr = s.charAt(i);
            if (!st.isEmpty() && (st.peek() == '[' && curr == ']'))
                st.pop();
            else st.push(curr);
        }
        if(st.isEmpty())    return 0;
        int m = st.size() / 2;
        if(m % 2 == 0)  return m / 2;
        return (m / 2) + 1;
    }
}