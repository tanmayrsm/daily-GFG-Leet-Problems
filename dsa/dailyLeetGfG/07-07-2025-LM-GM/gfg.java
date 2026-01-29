class Solution {
    public ArrayList<Integer> nextLargerElement(int[] arr) {
        // code here
        int n = arr.length, l = 0;
        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++)
            ans.add(-1);
        Stack<Integer> st = new Stack<>();

        st.add(arr[n - 1]);
        for (int i = n - 2; i >= 0; i--) {
            while(!st.isEmpty() && arr[i] >= st.peek())
                st.pop();
            if (!st.isEmpty()) {
                ans.set(i, st.peek());
            }
            st.add(arr[i]);
        }
        st.clear();

        for (int i = n - 1; i >= 0; i--) {
            if (ans.get(i) == -1) {
                while(l < i && arr[l] <= arr[i]) l++;
                if (l < i) {
                    ans.set(i, arr[l]);
                }
            }
        }

        return ans;
    }
}