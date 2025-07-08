import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

class Solution {
    public ArrayList<Integer> findGreater(int[] arr) {
        // code here
        Map<Integer, Integer> mp = new HashMap<>();
        ArrayList<Integer> ans = new ArrayList<>();
        Stack<Integer> st = new Stack<>();
        int n = arr.length;

        for (int x : arr) {
            ans.add(-1);
            mp.put(x, mp.getOrDefault(x, 0) + 1);
        }
        st.add(arr[n - 1]);
        for (int i = n - 2; i >= 0; i--) {
            while (!st.isEmpty() && mp.get(st.peek()) <= mp.get(arr[i]))
                st.pop();
            if (!st.isEmpty()) {
                ans.set(i, st.peek());
            }
            st.add(arr[i]);
        }
        st.clear();
        return ans;

    }
}


