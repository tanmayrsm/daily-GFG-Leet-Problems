class Solution {
    public ArrayList<Integer> calculateSpan(int[] arr) {
        // write code here
        ArrayList<Integer> res = new ArrayList<>();
        Stack<Integer> st = new Stack<>();
        res.add(1);
        st.push(0);
        
        for(int i=1;i<arr.length;i++){
            if(arr[i] < arr[st.peek()]){
                res.add(i-st.peek());
                st.push(i);
            }

            else{
                while((!st.isEmpty()) && (arr[i] >= arr[st.peek()])){
                    st.pop();
                }

                if(!st.isEmpty()){
                    res.add(i-st.peek());
                }
                else{
                    res.add(i+1);
                }

                st.push(i);
            }
        }
        return res;
    }
}