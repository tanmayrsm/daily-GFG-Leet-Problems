class Solution {
    public String fractionAddition(String expression) {
        int n = expression.length();
        expression += "e";
        StringBuilder sb = new StringBuilder();
        Stack<String> st = new Stack<>();
        for (int i = 0; i < n + 1; i++) {
            char curr = expression.charAt(i);
            if (curr == '+' || curr == '-' || curr == 'e') {
                if (st.isEmpty() && sb.isEmpty()) {
                    sb.append(curr + "");
                } else {
                    if (!st.isEmpty() && (st.peek().equals("-") || st.peek().equals("+"))) {
                        st.push(calculate(sb.toString(), st.pop(), st.pop()));
                    } else  {
                        st.push(sb.toString());
                    }
                    sb = new StringBuilder();
                    if (curr != 'e')  
                        st.push(curr + "");
                }
            } else sb.append(curr + "");
        }

        String top = st.pop();
        String[] s = top.split("\\/");
        int n1 = Integer.parseInt(s[0]), n2 = Integer.parseInt(s[1]);
        // System.out.println(n1 + "::" + n2);
        int gcdVal = gcd(Math.abs(n1), Math.abs(n2));
        n1 /= gcdVal;
        n2 /= gcdVal;
        return String.valueOf(n1) + "/" + String.valueOf(n2);
    }

    private static String calculate(String no1, String sign, String no2) {
        String[] s1 = no2.split("\\/"), s2 = no1.split("\\/");  // IMP -> 'a-b', not 'b-a'
        int n1 = Integer.parseInt(s1[0]), n2 = Integer.parseInt(s1[1]), n3 = Integer.parseInt(s2[0]), n4 = Integer.parseInt(s2[1]);
        int nume = 0, deno = n2 * n4, gcdVal = 0;
        if (sign.equals("+")) {
            nume = n1 * n4 + n2 * n3; 
        } else nume = n1 * n4 - n2 * n3;
        gcdVal = gcd(Math.abs(nume), Math.abs(deno));
        // System.out.println(n1 + "::" + n2 + "...." + n3 + "::" + n4);
        nume /= gcdVal; 
        deno /= gcdVal; 
        return String.valueOf(nume)  + "/" + String.valueOf(deno); 
    }

    private static int gcd (int a, int b) {
        if (a > b)  return gcd (b, a);
        if (a == 0) return b;
        return gcd (b % a, a);
    }
}