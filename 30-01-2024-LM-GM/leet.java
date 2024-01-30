class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> s = new Stack<Integer>();
        int ans = 0;
        Arrays.stream(tokens).forEach(token -> {
            if(!Character.isDigit(token.charAt(0)) && token.length() == 1) {
                char operand = token.charAt(0);
                int no2 = s.pop();
                int no1 = s.pop();
                if(operand == '+') {
                    s.push(no1 + no2);
                } else if(operand == '-') {
                    s.push(no1 - no2);
                } else if(operand == '*') {
                    s.push(no1*no2);
                } else {
                    s.push(no1/no2);
                }
            } else {
                s.push(Integer.parseInt(token));
            }
            
        });
        return s.pop();
    }
}