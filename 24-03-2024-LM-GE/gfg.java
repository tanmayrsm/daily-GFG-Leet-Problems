//User function Template for Java
class Solution {
    public Stack<Integer> insertAtBottom(Stack<Integer> st, int x) {
        Stack<Integer> temp = new Stack<>();
        while(!st.isEmpty()) 
            temp.push(st.pop());
        st.push(x);
        while(!temp.isEmpty())
            st.push(temp.pop());
        return st;
    }
}