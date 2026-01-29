class Solution {
    class Pair {
        Character c;
        Integer idx;
        Pair(int idx, char c) {
            this.c = c;
            this.idx = idx;
        }
    }
    public String minRemoveToMakeValid(String s) {
        int n = s.length();
        Stack<Pair> st = new Stack<>();
        StringBuilder ans = new StringBuilder();
        for(int i = 0; i < n; i++) {
            char curr = s.charAt(i);
            if(curr == ')' && !st.isEmpty() && st.peek().c == '(')
                st.pop();
            else if(curr == ')' || curr == '(')
                st.push(new Pair(i, curr));
        }

        for(int i = n - 1; i >= 0; i--) {
            if(!st.isEmpty() && st.peek().idx == i)
                st.pop();
            else ans.append(s.charAt(i) + "");
        }

        return ans.reverse().toString();
    }
}