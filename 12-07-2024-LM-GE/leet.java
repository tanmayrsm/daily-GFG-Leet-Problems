class Solution {
    public int maximumGain(String s, int x, int y) {
        // approach - greedy -> if y > x, first take out all possible 'ba's with help of stack
        // post that take out all 'ab's with stack
        Stack<Character> st = new Stack<>();
        int ans = 0, n = s.length(), m = -1;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char curr = s.charAt(i);
            if (st.isEmpty())   st.push(curr);
            else {
                if (curr == 'a' && st.peek() == 'b' && y >= x) {
                    ans += y;
                    st.pop();
                } else if (curr == 'b' && st.peek() == 'a' && y < x) {
                    ans += x;
                    st.pop();
                } else st.push(curr);
            }
        }
        while (!st.isEmpty())
            sb.append(st.pop() + "");
        
        // sb.reverse();    ...instead use loop from m-1 to 0
        m = sb.length();
        for (int i = m - 1; i >= 0; i--) {
            char curr = sb.charAt(i);
            if (st.isEmpty())   st.push(curr);
            else {
                if (curr == 'a' && st.peek() == 'b') {
                    ans += y;
                    st.pop();
                } else if (curr == 'b' && st.peek() == 'a') {
                    ans += x;
                    st.pop();
                } else st.push(curr);
            }
        }
        return ans;
    }
}


// Example 1:
// ab => x, ba => y
// Input: s = "cdbcbbaaabab", x = 4, y = 5
// Output: 19
// Explanation:
// - Remove the "ba" underlined in "cdbcbbaaabab". Now, s = "cdbcbbaaab" and 5 points are added to the score.
// - Remove the "ab" underlined in "cdbcbbaaab". Now, s = "cdbcbbaa" and 4 points are added to the score.
// - Remove the "ba" underlined in "cdbcbbaa". Now, s = "cdbcba" and 5 points are added to the score.
// - Remove the "ba" underlined in "cdbcba". Now, s = "cdbc" and 5 points are added to the score.
// Total score = 5 + 4 + 5 + 5 = 19.


// cdbcb ba aabab => 5
// cdbc ba abab   => 5
// cdbca ba b     => 5

// cdbcab        =>
// bacbdc <=reverse=> cdbcab