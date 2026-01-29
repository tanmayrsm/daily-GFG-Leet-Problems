class Solution {
    public int calculate(String s) {
        int n = s.length(), r = 0;
        String ans = "";
        Stack<String> st = new Stack<>();
        while (r < n) {
            char curr = s.charAt(r);
            if (Character.isDigit(r)) {
                StringBuilder sb = new StringBuilder();
                while(r < n && Character.isDigit(r)) {
                    curr = s.charAt(r++);
                    sb.append(curr + "");
                }
                // check pt
                char nextOperator = (r < n) ? s.charAt(r) : '#';
                if (!st.isEmpty()) {
                    char prevOperator = st.peek().charAt(0);
                    if (prevOperator == '/') {
                        resolve(st, sb.toString());
                    } else if (nextOperator == '/') {
                        st.push(sb.toString());
                    } else if (prevOperator == '*') {
                        resolve(st, sb.toString());
                    } else resolve(st, sb.toString());
                } else st.push(sb.toString());
            } else if (curr == ' ') {
                r++;
            } else {
                st.push(curr + "");
                r++;
            }
        }
        
        while(!st.isEmpty()) {
            ans = resolve(st, st.poll());
        }
        return Integer.parseInt(ans);
    }
    private String resolve(Stack<String> st, String nextNo) {
        if (st.isEmpty())   return "";
        String operator = st.poll();
        if (st.isEmpty())   return operator;
        
        String firstNo = st.poll();
        int first = Integer.parseInt(firstNo), sec = Integer.parseInt(nextNo);
        char ope = operator.charAt(0);
        if (ope == '/') st.push(String.valueOf(first / sec));
        else if (ope == '*') st.push(String.valueOf(first * sec));
        else if (ope == '-') st.push(String.valueOf(first - sec));
        else st.push(String.valueOf(first + sec));
        
    }
}

IF TOS == /
    doOpr
IF NEXT == /
    push
IF TOS == * and next dont exist || next == +, -, *
    doOpr
IF NEXT == *
    push
ELSE doOpr
    