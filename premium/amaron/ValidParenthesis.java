import java.util.Stack;

class Solution {
    public int longestValidParentheses(String s) {
        class Pair {
            Character chars;
            Integer index;
            Pair (Character chars, Integer index) {
                this.chars = chars;
                this.index = index;
            }
        }
        int n = s.length(), ans = 0;
        int[] arr = new int[n];
        boolean[] vis = new boolean[n];
        Stack<Pair> st = new Stack<>();
        for (int i = 0; i < n; i++) {
            char curr = s.charAt(i);
            if (curr == ')' && !st.isEmpty() && st.peek().chars == '(') {
                int indo = st.pop().index;
                ans = Math.max(ans, i - indo + 1);
                arr[indo] = ans;
            } else st.push(new Pair(curr, i));
        }
        for (int i = 0; i < n; i++) {
            int currAns = arr[i];
            if (arr[i] > 0 && !vis[i]) {
                int nextIndex = i + arr[i];
                while (nextIndex < n && arr[nextIndex] > 0) {
                    currAns += arr[nextIndex];
                    nextIndex += arr[nextIndex];
                    vis[nextIndex] = true;
                }
                ans = Math.max(ans, currAns);
            }
            
        }
        return ans;
    }
}

// )()())