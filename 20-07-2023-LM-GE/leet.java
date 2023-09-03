class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> st = new Stack<>();
        for(int x : asteroids) {
            if(st.isEmpty())
                st.push(x);
            else if ((st.peek() > 0 && x < 0) || (st.peek() < 0 && x > 0)) {
                // opposite moving asteroids
                boolean putElem = true;
                while(!st.isEmpty() && (st.peek() > 0 && x < 0)) {
                    int stackElem = Math.abs(st.peek());
                    int currElem = Math.abs(x);
                    if(stackElem == currElem) {
                        st.pop();
                        putElem = false;
                        break;
                    } else if (stackElem > currElem) {
                        putElem = false;
                        break;
                    } else st.pop();
                }
                if(putElem) {
                    st.push(x);
                }
            } else st.push(x);
        }

        int[] ans = new int[st.size()];
        int k = st.size() - 1;
        while(!st.isEmpty()) {
            ans[k--] = st.pop();
        }
        return ans;
    }
}