
class GfG {
    // Function to reverse first k elements of a queue.
    public Queue<Integer> modifyQueue(Queue<Integer> q, int k) {
        // add code here.
        int n = q.size();
        Stack<Integer> st = new Stack<>();
        int ct = 0;
        
        while(ct < k) {
            st.push(q.poll());
            ct++;
        }

        while(!st.isEmpty())
            q.offer(st.pop());
            
        while(ct < n) {
            q.offer(q.poll());
            ct++;
        }
            
        return q;
    }
}