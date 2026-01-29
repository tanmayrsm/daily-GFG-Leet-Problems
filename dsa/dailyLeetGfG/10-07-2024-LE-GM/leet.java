class Solution {
    public int minOperations(String[] logs) {
        Stack<String> st = new Stack<>();
        for(String log : logs) {
            if (!st.isEmpty() && log.equals("../"))
                st.pop();
            else if (!log.equals("../") && !log.equals("./"))
                st.push(log);
        }
        System.out.println(st);
        int n = st.size();
        st.clear();
        return n;
    }
}