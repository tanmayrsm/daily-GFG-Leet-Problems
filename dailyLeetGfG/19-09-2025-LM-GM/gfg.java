class Solution {
    public int minParentheses(String s) {
        // code here
        Stack<Character> st = new Stack<>();
        int c = 0;
        for(char ch:s.toCharArray()) {
            if(ch=='(') {
                st.push('(');
            }else{
                if(st.isEmpty()) c++;
                else st.pop();
            }
        }
        return c+st.size();
    }
}