import java.util.Stack;


class Solution {
    public String reverseParentheses(String s) {
        Stack<String> st = new Stack<>();
        int n = s.length(), m = -1;
        StringBuilder ans = new StringBuilder();
        List<String> a = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            char curr = s.charAt(i);
            if (curr != ')') {
                st.push(curr + "");
            } else {
                StringBuilder sb = new StringBuilder();
                if (!st.isEmpty()) {
                    String currCt = st.pop();
                    while (!st.isEmpty() && !currCt.equals("(")) {
                        sb.append(new StringBuilder(currCt).reverse());
                        currCt = st.pop();
                    }
                    st.push(sb.toString());
                }
            }
        }
        while (!st.isEmpty()) {
            a.add(st.pop());
        }
        m = a.size();
        for(int i = m - 1; i >= 0; i--)
            ans.append(a.get(i));
        return ans.toString();
    }
}



// s = "(ed(et(oc))el)"
// O: "leetcode"
// co
// et + co
// ed + octe + el
// edocte + el 

// (e,d,(e,t,co
// (e,d,(e,t,co
// (e,d,octe,e,l
// l,e,etco,d,e



// ed, et, oc => co
// ed, etco


// I can easily do it with stack
// but what if I dont want space to be used
