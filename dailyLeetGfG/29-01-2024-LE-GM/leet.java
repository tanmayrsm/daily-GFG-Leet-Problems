class MyQueue {
    Stack<Integer> s1;
    Stack<Integer> temp = new Stack<>();
        
    public MyQueue() {
        s1 = new Stack<Integer>();
    }
    
    public void push(int x) {
        s1.push(x);
    }
    
    public int pop() {
        // push all items from stack s1 to temp stack
        while(!s1.isEmpty()) {
            temp.push(s1.pop());
        }
        int elemToBePopped = temp.pop();    // pop last elem(stack s1's first elem')
        // push all items from temp to s1
        while(!temp.isEmpty()) {
            s1.push(temp.pop());
        }
        return elemToBePopped;
    }
    
    public int peek() {
        // push all items from stack s1 to temp stack
        while(!s1.isEmpty()) {
            temp.push(s1.pop());
        }
        int elemToBePeeked = temp.peek();    // pop last elem(stack s1's first elem')
        // push all items from temp to s1
        while(!temp.isEmpty()) {
            s1.push(temp.pop());
        }
        return elemToBePeeked;
    }
    
    public boolean empty() {
        return s1.isEmpty();
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */