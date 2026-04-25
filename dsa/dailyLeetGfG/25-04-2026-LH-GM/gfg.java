class Solution {
    public ArrayList<Integer> reducePairs(int[] arr) {
        // code here
        Stack<Integer> st = new Stack<>();
        int n = arr.length;
        st.push(arr[0]);
        for(int i = 1; i < n; i++) {
            if(st.isEmpty())    st.push(arr[i]);
            else {
                if(!st.isEmpty() &&
                        ((st.peek() < 0 && arr[i] > 0) || (st.peek() > 0 && arr[i] < 0))) {
                    boolean add = false;
                    while(!st.isEmpty()) {
                        if((st.peek() < 0 && arr[i] < 0) || (st.peek() > 0 && arr[i] > 0)) {
                            add = true;
                            break;
                        } else if(Math.abs(st.peek()) == Math.abs(arr[i])) {
                            st.pop();
                            add = false;
                            break;
                        } else if(Math.abs(st.peek()) < Math.abs(arr[i])) {
                            add = true;
                            st.pop();
                        } else {
                            add = false;
                            break;
                        }
                    }
                    if(add) st.push(arr[i]);
                }
                else st.push(arr[i]);
            }
        }
        return new ArrayList<>(st);
    }
}